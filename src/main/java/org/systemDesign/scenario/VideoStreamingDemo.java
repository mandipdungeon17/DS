package org.systemDesign.scenario;

import java.util.Arrays;
import java.util.List;

/*
🎯 Scenario 6 — Video Streaming Access and Lazy Loading

Context
A video streaming app has a `Video` class whose constructor eagerly loads the full
video file from disk/network (expensive) even if the video is never actually played.
Additionally: (a) some videos are premium and must only be playable by subscribed
users, and (b) every play must be logged for analytics.

Requirements
1. The expensive video load must not happen until `play()` is first actually called
   (not at object construction time) — lazy loading.
2. Premium videos must reject playback for non-subscribed users, with a clear message,
   without loading the video file at all in that case.
3. Every successful play must be logged (video id, user), without embedding logging
   code inside the core `Video` class.
4. All three concerns (lazy load, access control, logging) must be combinable/stackable
   on the same video, and client code should call `play()` identically regardless of
   how many of these wrappers are present.

Q&A (asked and answered during this exercise)
Q1: Name the pattern.
A1: Proxy — specifically three flavors combined: a Virtual Proxy (defers expensive
    object creation), a Protection Proxy (access control before delegating), and a
    Logging Proxy (side-effect around the call) — all implementing the same
    `Playable` interface as the real `Video`.

Q2: What was the root structural bug when this was first attempted, and what fixed it?
A2: The proxy classes were written to hold/reference the concrete `Video` class
    directly instead of the shared `Playable` interface. That prevented proxies from
    wrapping OTHER proxies (no stacking), collapsing Virtual+Protection+Logging into
    one tangled class instead of three independently stackable layers. Fix: every
    proxy's field and constructor parameter must be typed to `Playable`, not `Video`.

Q3: Discriminator vs Decorator — this scenario "looks like" Decorator since it wraps
    and stacks. What's the actual test that says Proxy, not Decorator?
A3: "Can the wrapped object now do something it couldn't do before?" Here: No — play()
    still just plays a video; the proxies only gate WHETHER/WHEN/HOW it's allowed to
    run (lazy-create, permission check, log), they add no new domain capability. That
    answer of "no new capability" is what makes it Proxy, not Decorator.

Ruled out similar patterns:
- Decorator: intent here is controlling access/timing/side-effects around an unchanged
  capability, not adding new domain behavior to the object.
- Doing lazy-loading/caching only on the client side: this leaks infrastructure
  concerns (should I load yet? am I allowed?) into every caller instead of centralizing
  it behind the same `Playable` interface the real object already implements.

Rule of thumb:
- Every proxy implements the SAME interface as the real subject, and its field/
  constructor is typed to that interface — never to the concrete real-subject class —
  so proxies can wrap the real object OR wrap each other.
- Virtual proxy: only instantiate/load the real object inside the method that actually
  needs it (first call), not in the proxy's own constructor.
- When stacking Protection + Logging, decide ORDER deliberately: if denied access must
  still be logged, Logging must wrap Protection (log sits outermost); if you only want
  to log successful plays, Protection wraps closer to the real object.
*/
public class VideoStreamingDemo {
    public static void main(String[] args) {
        System.out.println("--- Building catalog of 500 videos ---");
        long start = System.currentTimeMillis();
        for (int i = 0; i < 500; i++) {
            new VirtualVideoProxy("video" + i);   // nothing loaded
        }
        System.out.println("Catalog built in " + (System.currentTimeMillis() - start) + "ms\n");

        System.out.println("--- Free user tries premium video ---");
        Playable forFreeUser = new LoggingProxy(
                new ProtectionProxy(
                        new VirtualVideoProxy("v1"), "GUEST"), "GUEST");
        forFreeUser.play();

        System.out.println("\n--- Subscriber plays (first time) ---");
        Playable forSubscriber = new LoggingProxy(
                new ProtectionProxy(
                        new VirtualVideoProxy("v1"), "USER1"), "USER1");
        forSubscriber.play();

        System.out.println("\n--- Same subscriber plays again ---");
        forSubscriber.play();
    }
}

interface Playable {
    void play();
    String getTitle();
}

class Video implements Playable{
    private final String title;
    private final byte[] hdThumbnail;   // 2 MB, fetched from CDN
    private final byte[] videoFile;     // 800 MB, fetched from CDN

    Video(String id) {
        System.out.println("  [Video] Loading " + id + " from CDN...");
        this.title = fetchTitle(id);           // fast, 5ms
        this.hdThumbnail = fetchThumbnail(id); // slow, 300ms
        this.videoFile = fetchVideo(id);       // very slow, 8 seconds
        System.out.println("  [Video] Loaded " + id);
    }

    @Override
    public void play() { System.out.println("  [Video] Streaming " + title); }
    @Override
    public String getTitle() { return title; }

    private String fetchTitle(String id)   { return "Title-" + id; }
    private byte[] fetchThumbnail(String id) { return new byte[0]; }
    private byte[] fetchVideo(String id)     { return new byte[0]; }
}

// VIRTUAL PROXY — defers construction until first play()
class VirtualVideoProxy implements Playable{
    private final String id;
    private Video realVideo;

    VirtualVideoProxy(String id) {
        this.id = id;
    }
    @Override
    public void play() {
        if(realVideo == null) {
            realVideo = new Video(this.id);
        }
        realVideo.play();
    }
    @Override
    public String getTitle() {
        return realVideo == null ? "Title-" + id : realVideo.getTitle();
    }
}

// PROTECTION PROXY — rejects before delegating downstream
class ProtectionProxy implements Playable{
    List<String> SUBSCRIBERS = Arrays.asList("USER1",  "USER2", "USER3");

    private final Playable playable;
    private final String user;

    ProtectionProxy(Playable playable, String user) {
        this.playable = playable;
        this.user = user;
    }
    @Override
    public void play() {
        if(!SUBSCRIBERS.contains(user)) {
            System.out.println("  [Auth] DENIED for " + user + " — subscription required");
        } else {
            System.out.println("  [Auth] ALLOWED for " + user);
            playable.play();
        }
    }
    @Override
    public String getTitle() {
        return playable.getTitle();
    }
}

// LOGGING PROXY — records every attempt, allowed or denied
class LoggingProxy implements Playable{
    private final Playable playable;
    private final String user;

    LoggingProxy(Playable playable, String user) {
        this.playable = playable;
        this.user = user;
    }
    @Override
    public void play() {
        System.out.println("  [Audit] " + user + " requested play at " + System.currentTimeMillis());
        playable.play();
        System.out.println("  [Audit] " + user + " request completed");
    }
    @Override
    public String getTitle() {
        return playable.getTitle();
    }
}