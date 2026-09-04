package org.systemDesign.scenario;

import java.io.File;
import java.util.List;

/*
🎯 Scenario 9 — Video Upload Processing Pipeline

Context
Uploading a video to a platform requires several subsystem calls, always in this
order: VirusScanner.scan(file), TranscoderService.transcode(file, resolutions),
ThumbnailGenerator.generate(file), MetadataStore.save(videoInfo),
CdnUploader.upload(transcodedFiles), NotificationService.notifyOwner(videoId).
Callers (e.g. a CLI tool, a web controller) currently duplicate this entire 6-step
sequence themselves, in the right order, every time they need to upload a video.

Requirements
1. Callers should be able to upload a video with a single simple call, without
   knowing or manually sequencing all 6 subsystem steps.
2. Each subsystem must remain independently usable/testable on its own (they stay
   public — this is not about restricting access).
3. Adding a 7th step later (e.g. AI content moderation) must be a small, centralized
   change, not an edit scattered across every caller.
4. main must demonstrate at least two different callers (e.g. a CLI path and a web
   controller path) both using the single simplified entry point successfully.

Q&A (asked and answered during this exercise)
Q1: Name the pattern.
A1: Facade — a new class (`VideoUploadFacade`) exposes one simple method
    (`uploadVideo(file)`) that internally calls all 6 subsystems in the correct order,
    so callers stop duplicating that orchestration themselves.

Q2: Why is this Facade and not Decorator (the pattern this scenario sits close to)?
A2: Facade might seem plausible because a caller "only wants to use one simple API and
    not deal with all the subsystem APIs and sequential steps directly." Decorator was
    considered but ruled out because of requirement 4 (multiple distinct caller types
    reusing the exact same fixed sequence) — Decorator is about layering additive
    behavior onto ONE object dynamically, not about hiding a fixed multi-step
    orchestration behind one entry point for many callers.

Q3: Why is this Facade and not Template Method (the other close lookalike)?
A3: Template Method implements the sequential steps inside an abstract class's `final`
    method, forcing OTHER classes to `extend` that abstract class and implement its
    abstract methods — which drags in unrelated method definitions that unrelated
    subclasses are then forced to implement. Facade instead uses plain composition:
    it holds references to the subsystem objects and calls them in order — no
    inheritance relationship is forced onto callers at all.

Q4 (correction applied): Can a Facade contain business logic?
A4: Correction: yes, a Facade implementation CAN contain business logic if the
    scenario calls for it — nothing about the pattern's mechanics forbids it. However,
    the STRONGLY PREFERRED/idiomatic form keeps the Facade as pure orchestration/
    delegation with little to no business logic of its own, so that logic stays owned
    by the subsystems it coordinates — "can" is not the same as "should."

Bugs parked (explicitly not fixed per user's request — "park the bugs"):
- `main` originally called `controller.upload()` twice instead of exercising both the
  CLI path and the web-controller path separately (now corrected to call `cli.upload()`
  and the controller path distinctly).
- Several subsystem method stubs (VirusScanner, StorageClient, etc.) have empty bodies
  with no `println`, so running the pipeline currently produces no visible per-step
  output even though the calls do occur in the correct order.

Ruled out similar patterns:
- Template Method: no inheritance-based algorithm variation is needed here; this is one
  orchestrator entry point delegating to independent subsystem objects via composition,
  not a `final` skeleton method with abstract/hook steps overridden by subclasses.
- Decorator: this is not about adding layered/stackable behavior to one object's
  interface; it's about hiding a fixed multi-step orchestration behind a single
  simplified call for many unrelated callers.

Rule of thumb:
- Facade exposes one (or few) simple method(s) and delegates to subsystem APIs in the
  correct order — via composition, holding references to those subsystems.
- Keep orchestration/setup/teardown centralized in the facade so callers never
  duplicate the sequence themselves.
- Subsystems remain publicly accessible on their own — Facade is a convenience layer
  for the common case, not an access-restriction mechanism (that distinction is what
  separates it from Proxy).
- Prefer keeping the Facade itself free of business logic (pure delegation), even
  though the pattern technically permits some.
*/
public class VideoUploadDemo {
    public static void main(String[] args) {
        File file = new File("");
        WebController controller = new WebController();
        controller.upload(file, "user1", "book");

        CLIController cli = new CLIController();
        cli.upload(file, "user1", "book");
    }
}

class AndroidController{
    private final VideoUploadFacade facade = new VideoUploadFacade();

    public void upload(File file, String userId, String title) {
        facade.uploadVideo(file, userId, title);
    }
}
class WebController{
    private final VideoUploadFacade facade = new VideoUploadFacade();

    public void upload(File file, String userId, String title) {
        facade.uploadVideo(file, userId, title);
    }
}
class CLIController{
    private final VideoUploadFacade facade = new VideoUploadFacade();

    public void upload(File file, String userId, String title) {
        facade.uploadVideo(file, userId, title);
    }
}
class VideoUploadFacade {

    public void uploadVideo(File file, String userId, String title) {
        VirusScanner scanner = new VirusScanner();
        scanner.loadDefinitions();
        ScanResult result = scanner.scan(file);
        if (result.isInfected()) throw new RuntimeException("Infected");

        StorageClient storage = new StorageClient("ap-south-1", "videos-bucket");
        storage.connect();
        String rawKey = storage.upload(file);
        storage.disconnect();

        TranscodeService transcoder = new TranscodeService();
        transcoder.setPresets(List.of("480p", "720p", "1080p"));
        String jobId = transcoder.submit(rawKey);
        transcoder.waitForCompletion(jobId);

        ThumbnailGenerator thumbGen = new ThumbnailGenerator();
        thumbGen.setFrameOffset(3);
        String thumbKey = thumbGen.generate(rawKey);

        MetadataDb db = new MetadataDb();
        db.openConnection();
        db.insertVideo(userId, title, rawKey, thumbKey, jobId);
        db.closeConnection();

        SearchIndexer indexer = new SearchIndexer();
        indexer.index(userId, title, rawKey);

        NotificationService notifier = new NotificationService();
        notifier.notifyUploadComplete(userId, title);
    }
}

class VirusScanner {
    public void loadDefinitions() {}
    public ScanResult scan(File file) { return new ScanResult(); }
}
class ScanResult {
    public boolean isInfected() {return false;}
}

class StorageClient {
    StorageClient(String username, String bucket) {}
    public void connect() {}
    public void disconnect() {}
    public String upload(File file) { return null; }
}

class TranscodeService {
    public String submit(String rawKey) {return null;}
    public void waitForCompletion(String jobId) {}
    public void setPresets(List<String> presets) {}
}

class ThumbnailGenerator {
    public String generate(String rawKey) { return null; }
    public void setFrameOffset(int offset) {}
}

class MetadataDb {
    public void openConnection() {}
    public void closeConnection() {}
    public void insertVideo(String userId, String title, String rawKey, String thumbKey, String jobId) {}
}

class SearchIndexer {
    public void index(String userId, String title, String rawKey) {}
}

class NotificationService {
    public void notifyUploadComplete(String userId, String title) {}
}