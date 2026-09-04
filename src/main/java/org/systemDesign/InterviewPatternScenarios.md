# Interview Pattern Scenarios (20 New, Java-Oriented)

🎯 Scenario 1 — Unified KYC Verification Across Legacy Vendors

**Context**  
Your onboarding service must verify users using three existing KYC SDKs with incompatible contracts, payloads, and response codes. Product wants one internal `KycProvider` API returning a normalized decision (`APPROVED`, `REVIEW`, `REJECTED`) plus reason codes.

**Requirements**
1. Expose one common interface for all KYC providers, independent of vendor DTOs.
2. Normalize different vendor status models and error formats into a single internal response object.
3. Unit and data conversions (DOB formats, country codes, masked IDs) must be hidden from calling service code.
4. Adding a fourth vendor must require only a new class, with no edits in existing provider implementations or orchestration flow.

**Traps**
- Putting `if (vendor == X)` inside onboarding service and leaking vendor enums everywhere.
- Returning raw vendor response objects from internal APIs.
- Building one “mega facade” that still does not solve signature incompatibility.

**Deliverable**  
`src\main\java\org\systemDesign\scenario\LegacyKycIntegrationDemo.java`

**Answer before coding:**
1. Name the pattern.
2. Why not `Facade`? (one sentence)
3. Why not `Strategy`? (one sentence)
4. Precise implementation expectation (how request/response translation is encapsulated per vendor adapter).

---

🎯 Scenario 2 — Common Signed URL Generator for Multi-Cloud Storage

**Context**  
Your download API supports AWS S3, Azure Blob, and on-prem MinIO. Each client library uses different method names, expiration units, and request objects. Internal platform teams need one stable API: `generateSignedUrl(objectKey, httpMethod, ttlSeconds)`.

**Requirements**
1. Public service code must call one provider-neutral interface.
2. Provider-specific time units, credential types, and path styles must be converted internally.
3. Output must be normalized (URL + expiry epoch + provider name) for auditing.
4. Adding GCS later must not change existing loop/selection code.

**Traps**
- Polluting caller code with provider-specific pre-processing.
- Designing a target interface that includes provider-only fields (“just in case”).
- Mistaking “algorithm choice” for API-shape adaptation.

**Deliverable**  
`src\main\java\org\systemDesign\scenario\SignedUrlAdapterDemo.java`

**Answer before coding:**
1. Name the pattern.
2. Why not `Strategy`? (one sentence)
3. Why not `Proxy`? (one sentence)
4. Precise implementation expectation (common target interface + one wrapper per cloud SDK with full conversion logic inside wrapper).

---

🎯 Scenario 3 — Fraud Model Serving with Lazy Load + Access Gate + Audit  

**Context**  
A real fraud model object is expensive to initialize (loads model + warm-up tensors). Only risk-engine services may invoke scoring, and every successful inference must be audited. Client code should still depend on `FraudScorer.score(txn)` only.

**Requirements**
1. Model loading must happen only on first real score request.
2. Unauthorized callers must be rejected before touching the heavy model object.
3. Successful scores must be logged with tenant/request IDs.
4. Client code should call the same interface regardless of wrappers.

**Traps**
- Loading model in constructor “for simplicity,” defeating lazy behavior.
- Embedding auth checks in every caller instead of a control layer.
- Confusing additive capability with controlled access/timing.

**Deliverable**  
`src\main\java\org\systemDesign\scenario\FraudScoringProxyDemo.java`

**Answer before coding:**
1. Name the pattern.
2. Why not `Decorator`? (one sentence)
3. Why not `Facade`? (one sentence)
4. Precise implementation expectation (same subject interface; virtual/protection/logging proxies delegating to real scorer).

---

🎯 Scenario 4 — Profile Repository with Read Cache and Write Protection  

**Context**  
Recommendation service reads customer profiles from a remote profile API. Reads are high volume and repeat-heavy. This service must not perform writes, and hot profiles should be cached for short TTL. Existing callers already depend on `CustomerProfileRepository`.

**Requirements**
1. Keep the same repository interface for callers.
2. Cache read calls transparently with TTL and key-based lookup.
3. Block write operations for non-privileged consumers with explicit errors.
4. Cache/protection logic must not leak into business services.

**Traps**
- Returning a different interface just to add caching behavior.
- Placing cache maps directly in recommendation business logic.
- Using Adapter even though API signatures are already compatible.

**Deliverable**  
`src\main\java\org\systemDesign\scenario\ProfileRepositoryProxyDemo.java`

**Answer before coding:**
1. Name the pattern.
2. Why not `Decorator`? (one sentence)
3. Why not `Adapter`? (one sentence)
4. Precise implementation expectation (proxy implements repository interface and controls access + memoization before delegating).

---

🎯 Scenario 5 — One-Click Production Rollback Orchestrator  

**Context**  
Incident response currently requires operators to run 6 subsystems manually in order: traffic freeze, feature-flag rollback, service rollout rollback, DB compatibility check, cache purge, incident annotation. Steps are repeated by CLI and internal admin API callers.

**Requirements**
1. Provide a single entry-point method for rollback execution.
2. Preserve strict execution order in one centralized place.
3. Keep subsystem APIs independently usable outside this flow.
4. Future rollback steps should be added once, not copied to each caller.

**Traps**
- Re-implementing the same sequence in every caller (“thin wrappers” only).
- Turning this into inheritance hierarchy when there is only one common flow.
- Assuming facade must hide subsystems completely.

**Deliverable**  
`src\main\java\org\systemDesign\scenario\ReleaseRollbackFacadeDemo.java`

**Answer before coding:**
1. Name the pattern.
2. Why not `Template Method`? (one sentence)
3. Why not `Chain of Responsibility`? (one sentence)
4. Precise implementation expectation (one orchestrator class composing subsystems and exposing one high-level rollback API).

---

🎯 Scenario 6 — Merchant Onboarding Pipeline Simplification  

**Context**  
Merchant onboarding requires document OCR, sanctions screening, bank-account verification, ledger-account creation, and welcome-notification dispatch. Both admin portal and bulk-import jobs currently duplicate this sequence.

**Requirements**
1. Callers must start onboarding using one high-level method.
2. Steps must run in domain-correct order and return one consolidated result.
3. Underlying subsystem services remain separately testable and callable.
4. New onboarding steps (e.g., GST verification) should be added centrally.

**Traps**
- Modeling this as Adapter despite no incompatible API translation requirement.
- Modeling each step as observer event where ordering guarantees become weak.
- Spreading orchestration responsibility across controllers/jobs.

**Deliverable**  
`src\main\java\org\systemDesign\scenario\MerchantOnboardingFacadeDemo.java`

**Answer before coding:**
1. Name the pattern.
2. Why not `Adapter`? (one sentence)
3. Why not `Observer`? (one sentence)
4. Precise implementation expectation (facade owns orchestration and delegates to composed service collaborators in order).

---

🎯 Scenario 7 — Export Payload Add-ons (Compression, Encryption, Signature)  

**Context**  
A reporting API produces a base payload. Enterprise customers can enable any combination of payload add-ons: GZIP compression, AES encryption, and digital signature envelope. These add-ons should be enabled per client contract at runtime.

**Requirements**
1. Base payload generation must work independently.
2. Add-ons must be stackable in different combinations without subclass explosion.
3. Output generation should be computed through delegation (no redundant shadow state).
4. New add-ons should be introduced by adding new wrapper classes only.

**Traps**
- Creating subclasses for each combination (`CompressedEncryptedSignedPayload` etc.).
- Treating wrappers only as access-control gates (wrong intent).
- Implementing one large “options if-else” method.

**Deliverable**  
`src\main\java\org\systemDesign\scenario\SecurePayloadDecoratorDemo.java`

**Answer before coding:**
1. Name the pattern.
2. Why not `Proxy`? (one sentence)
3. Why not `Chain of Responsibility`? (one sentence)
4. Precise implementation expectation (decorators implement same interface and add behavior before/after delegated `render()`).

---

🎯 Scenario 8 — Search Ranking Boost Layers

**Context**  
Search already has a base relevance score. Product now wants additive scoring layers: freshness boost, in-stock boost, premium-seller boost, and geographic-affinity boost. Any subset may apply per request, and a score explanation string is needed.

**Requirements**
1. Keep a base scorer that can run alone.
2. Boost layers must be composable and independently testable.
3. Both score and explanation text must be derived through wrapped delegation.
4. Adding a new boost should not modify existing scorer/boost classes.

**Traps**
- Encoding all boosts in one strategy class with many flags.
- Modeling boosts as mutually exclusive algorithm choices.
- Using composite tree semantics when this is one wrapped scorer chain.

**Deliverable**  
`src\main\java\org\systemDesign\scenario\SearchBoostDecoratorDemo.java`

**Answer before coding:**
1. Name the pattern.
2. Why not `Strategy`? (one sentence)
3. Why not `Composite`? (one sentence)
4. Precise implementation expectation (base scorer + wrappers each adding score delta and explanation fragment).

---

🎯 Scenario 9 — Subscription Billing Lifecycle  

**Context**  
A SaaS subscription moves through `TRIAL`, `ACTIVE`, `GRACE_PERIOD`, `SUSPENDED`, `CANCELLED`. Actions include `paymentSuccess`, `paymentFailure`, `renew`, `cancel`, and `reactivate`. Legal transitions depend on current state.

**Requirements**
1. Context class must not contain switch/if chains on subscription status.
2. Illegal actions should fail with clear state-aware errors.
3. New lifecycle stage (e.g., `PAUSED`) should be addable with minimal edits.
4. Transition authority should live in state objects, not in callers.

**Traps**
- Treating status as enum + giant transition matrix in one service class.
- Letting caller choose “next state” directly.
- Mistaking caller-selected behavior swapping for state-driven transition behavior.

**Deliverable**  
`src\main\java\org\systemDesign\scenario\SubscriptionStateDemo.java`

**Answer before coding:**
1. Name the pattern.
2. Why not `Strategy`? (one sentence)
3. Why not `Chain of Responsibility`? (one sentence)
4. Precise implementation expectation (context delegates actions to current state object; state decides transition and sets next state).

---

🎯 Scenario 10 — Progressive Deployment Lifecycle Controller  

**Context**  
A deployment goes through `DRAFT -> APPROVED -> CANARY -> FULL_ROLLOUT -> COMPLETED`, with optional `PAUSED` and `ROLLED_BACK`. Actions such as `approve`, `startCanary`, `promote`, `pause`, `resume`, `rollback`, `complete` are legal only in specific stages.

**Requirements**
1. Deployment object must expose action methods without internal status switch blocks.
2. Invalid actions must produce explicit “cannot X from Y” failures.
3. Transition rules must be encapsulated per stage.
4. Adding `VERIFICATION` state should not require rewriting all existing transitions.

**Traps**
- Implementing action legality in a centralized util with dozens of `if`s.
- Modeling this as one selected rollout algorithm instead of stateful transitions.
- Collapsing all stage rules into booleans on one class.

**Deliverable**  
`src\main\java\org\systemDesign\scenario\DeploymentStateDemo.java`

**Answer before coding:**
1. Name the pattern.
2. Why not `Strategy`? (one sentence)
3. Why not `Template Method`? (one sentence)
4. Precise implementation expectation (state classes own allowed actions and next-state mapping; context just delegates).

---

🎯 Scenario 11 — Feature Flag Change Broadcasting  

**Context**  
When a feature flag changes, multiple systems must react: cache invalidation, websocket push to clients, audit event emit, and experiment metrics update. New listeners appear often and should attach without changing flag-store core logic.

**Requirements**
1. Flag store must publish updates to dynamic subscribers.
2. Failure in one listener must not prevent others from receiving the update.
3. Runtime subscribe/unsubscribe should be supported.
4. Flag store should depend on listener abstraction, not concrete classes.

**Traps**
- Hardcoding fixed notifier calls in the flag update method.
- Building a first-match handler chain when all listeners must run.
- Treating this as one pluggable algorithm.

**Deliverable**  
`src\main\java\org\systemDesign\scenario\FeatureFlagObserverDemo.java`

**Answer before coding:**
1. Name the pattern.
2. Why not `Chain of Responsibility`? (one sentence)
3. Why not `Strategy`? (one sentence)
4. Precise implementation expectation (subject maintains list of observers and notifies all on state change with per-observer failure isolation).

---

🎯 Scenario 12 — IoT Telemetry Event Fan-Out  

**Context**  
Device telemetry events (`temperature_high`, `battery_low`, `disconnect`) should trigger parallel reactions: alerting, incident ticket creation, auto-remediation workflow, and long-term analytics ingestion. Handlers evolve independently.

**Requirements**
1. Telemetry publisher must allow independent listeners to register.
2. Every subscribed listener should receive each relevant event.
3. Listener exceptions must be isolated and reported without stopping fan-out.
4. New listener modules must integrate without edits to publisher internals.

**Traps**
- Treating event fan-out as command queue replay.
- Building a single “winner” pipeline that stops after first handler.
- Coupling publisher to concrete integrations.

**Deliverable**  
`src\main\java\org\systemDesign\scenario\TelemetryObserverDemo.java`

**Answer before coding:**
1. Name the pattern.
2. Why not `Command`? (one sentence)
3. Why not `Chain of Responsibility`? (one sentence)
4. Precise implementation expectation (event subject + observer interface + concrete subscribers handling telemetry independently).

---

🎯 Scenario 13 — Webhook Retry Policy Per Merchant SLA  

**Context**  
Webhook delivery must support merchant-specific retry behavior: no retry, fixed interval retry, exponential backoff with jitter. Policy may change at runtime via merchant config.

**Requirements**
1. Delivery service should call one retry-policy interface.
2. Policy should be replaceable at runtime per merchant/request.
3. Adding new policy (e.g., capped exponential) must not change delivery service internals.
4. Policy choice should remain external; service should not inspect policy types.

**Traps**
- Encoding retry rules in giant switch statement in delivery service.
- Letting policy object mutate into another policy internally without explicit selection.
- Forcing all policies into one inheritance template with mostly divergent logic.

**Deliverable**  
`src\main\java\org\systemDesign\scenario\WebhookRetryStrategyDemo.java`

**Answer before coding:**
1. Name the pattern.
2. Why not `State`? (one sentence)
3. Why not `Template Method`? (one sentence)
4. Precise implementation expectation (context delegates retry decision/timing to injected strategy object selected by configuration).

---

🎯 Scenario 14 — Delivery Route Objective Selection  

**Context**  
Same route graph can be optimized differently based on product requirement: fastest ETA, lowest fuel cost, or lowest CO2 emission. Dispatch system should switch optimization objective per order type and experimentation flags.

**Requirements**
1. Route planner should depend on a stable objective interface.
2. Runtime switching between objectives must be supported without recreating planner internals.
3. New objective algorithms should be addable independently.
4. Planner should not contain objective-specific branches.

**Traps**
- Modeling objectives as stacked add-ons that all execute together.
- Letting planner transition objectives by itself based on internal lifecycle.
- Baking algorithm choice into graph model classes.

**Deliverable**  
`src\main\java\org\systemDesign\scenario\RouteStrategyDemo.java`

**Answer before coding:**
1. Name the pattern.
2. Why not `State`? (one sentence)
3. Why not `Decorator`? (one sentence)
4. Precise implementation expectation (one route context invoking exactly one selected objective strategy per planning request).

---

🎯 Scenario 15 — Login Risk Decision Pipeline  

**Context**  
Authentication pipeline includes checks in sequence: IP reputation, device trust, velocity/rate anomaly, optional step-up challenge. Each check can `ALLOW`, `DENY`, or `PASS_TO_NEXT`.

**Requirements**
1. Each check class should know only its own rule.
2. Pipeline ordering must be externally configurable.
3. Processing should stop at first decisive `ALLOW`/`DENY`.
4. If all checks pass-through, return explicit default decision.

**Traps**
- Running all checks always, even after decisive deny.
- Hardcoding whole pipeline logic in one monolithic method.
- Treating this as event broadcast where all handlers must execute.

**Deliverable**  
`src\main\java\org\systemDesign\scenario\LoginRiskChainDemo.java`

**Answer before coding:**
1. Name the pattern.
2. Why not `Observer`? (one sentence)
3. Why not `Strategy`? (one sentence)
4. Precise implementation expectation (linked handlers returning tri-state decision; forward only on `PASS_TO_NEXT`).

---

🎯 Scenario 16 — Pull Request Merge-Gate Validation Chain  

**Context**  
Before merge, PR must pass checks: title convention, linked ticket, unit tests status, license compliance, security scan policy. Different repositories enable different subsets and orders.

**Requirements**
1. Add/remove/reorder validators without changing existing validator code.
2. First failure should stop chain and return actionable failure reason.
3. Chain should be assembled by configuration/factory, not inside validators.
4. Must handle “no validator accepted responsibility” with explicit fallback.

**Traps**
- Implementing a fixed sequential facade that always runs everything.
- Using template skeleton when flow should short-circuit on first failure.
- Allowing validators to inspect each other’s internals.

**Deliverable**  
`src\main\java\org\systemDesign\scenario\PrGateChainDemo.java`

**Answer before coding:**
1. Name the pattern.
2. Why not `Facade`? (one sentence)
3. Why not `Template Method`? (one sentence)
4. Precise implementation expectation (validator handlers linked at runtime; each handles or forwards with explicit result object).

---

🎯 Scenario 17 — Payout Operation Queue with Undo/Compensation  

**Context**  
Finance ops needs to queue payout operations (`reserve`, `debit`, `markPaid`, `notify`) for delayed execution, audit them, and support compensating undo for selected steps in case of downstream failure.

**Requirements**
1. Encapsulate each operation as an executable object with receiver reference.
2. Scheduler/invoker should execute queued operations without knowing operation internals.
3. Support command history for compensation/undo where defined.
4. Macro (batch payout) should be executable as one command unit.

**Traps**
- Keeping operation logic in invoker with enum switches.
- Confusing one selected policy algorithm with queued request objects.
- Building observer fan-out when exact execution order matters.

**Deliverable**  
`src\main\java\org\systemDesign\scenario\PayoutCommandDemo.java`

**Answer before coding:**
1. Name the pattern.
2. Why not `Strategy`? (one sentence)
3. Why not `Chain of Responsibility`? (one sentence)
4. Precise implementation expectation (command interface with execute/undo; invoker manages queue/history; macro command composes child commands).

---

🎯 Scenario 18 — Warehouse Robot Task Sequencer  

**Context**  
Robot controller must queue tasks (`moveTo`, `pick`, `drop`, `dockCharge`) from multiple upstream systems, replay failed batches, and support dry-run simulation mode without changing task emitters.

**Requirements**
1. Task requests must be represented as command objects.
2. Invoker should support queueing, replay, and dry-run execution.
3. Receivers (robot subsystems) must stay decoupled from scheduling concerns.
4. New robot task type should be addable without changing invoker logic.

**Traps**
- Modeling this as observer notifications (order and replay semantics get blurred).
- Embedding task execution branches directly in queue processor.
- Using template inheritance though tasks are independent request objects.

**Deliverable**  
`src\main\java\org\systemDesign\scenario\RobotCommandDemo.java`

**Answer before coding:**
1. Name the pattern.
2. Why not `Observer`? (one sentence)
3. Why not `Template Method`? (one sentence)
4. Precise implementation expectation (task command objects + invoker queue + receiver APIs; optional composite batch command for grouped execution).

---

🎯 Scenario 19 — Bank Settlement Processor Skeleton  

**Context**  
Daily settlement processing has fixed critical flow: acquire lock, load pending transactions, validate batch, apply settlement entries, publish reconciliation summary, release lock. Bank-specific formats differ in validation and posting details.

**Requirements**
1. Preserve non-negotiable execution order in one final method.
2. Shared steps (lock/release, summary publish) must live in base class.
3. Variable steps must be overridable per bank processor subtype.
4. Optional hook for post-settlement archival should default to no-op.

**Traps**
- Exposing sequence method for override and allowing reordering.
- Modeling as Strategy when only parts vary, but skeleton must stay fixed.
- Misusing Facade when the core need is invariant algorithm order control.

**Deliverable**  
`src\main\java\org\systemDesign\scenario\SettlementTemplateDemo.java`

**Answer before coding:**
1. Name the pattern.
2. Why not `Strategy`? (one sentence)
3. Why not `Facade`? (one sentence)
4. Precise implementation expectation (abstract base with final `processSettlement()` calling concrete/abstract/hook steps).

---

🎯 Scenario 20 — Data Retention Purge Workflow  

**Context**  
Compliance purge for user data follows mandatory sequence: authorize purge request, snapshot metadata, delete primary store data, delete secondary indexes, emit audit trail, notify requester. Product verticals differ in deletion details and notification format.

**Requirements**
1. Mandatory sequence must be enforced centrally.
2. Common legal/audit steps must not be overrideable.
3. Product-specific deletion logic must be pluggable through subclass hooks/abstract methods.
4. Optional hook for post-purge cache warmup should be available.

**Traps**
- Modeling each step as independent chain handlers that can accidentally skip legal order.
- Treating purge stage labels as runtime transitions rather than one fixed algorithm.
- Placing all variant logic in `if (productType)` branches.

**Deliverable**  
`src\main\java\org\systemDesign\scenario\DataRetentionTemplateDemo.java`

**Answer before coding:**
1. Name the pattern.
2. Why not `Chain of Responsibility`? (one sentence)
3. Why not `State`? (one sentence)
4. Precise implementation expectation (base class final workflow + abstract step implementations + optional hooks for vertical-specific extensions).

---

## Final Validation Summary

| Scenario | Pattern | Domain | Primary discriminator |
|---|---|---|---|
| 1 | Adapter | Fintech compliance | Incompatible vendor APIs translated to one internal contract. |
| 2 | Adapter | Cloud platform/storage | Different SDK signatures/units normalized behind one target interface. |
| 3 | Proxy | ML/risk platform | Same interface, but access/timing/audit control around heavy real object. |
| 4 | Proxy | Backend profile service | Transparent access control + caching without changing repository API. |
| 5 | Facade | DevOps incident response | One simplified orchestration entry point over many subsystems. |
| 6 | Facade | Merchant onboarding | Centralized ordered workflow for multiple callers, not API adaptation. |
| 7 | Decorator | Secure export product | Stackable additive behavior (compress/encrypt/sign) on same payload interface. |
| 8 | Decorator | Search/recommendation | Incremental score/explanation augmentation via wrappers. |
| 9 | State | SaaS billing lifecycle | Current state decides legal actions and next transitions. |
| 10 | State | Progressive delivery | Stage-specific behavior with transition authority inside states. |
| 11 | Observer | Feature flag platform | One change event fan-outs to all subscribed listeners. |
| 12 | Observer | IoT operations | Publisher broadcasts telemetry to many independent subscribers. |
| 13 | Strategy | Webhook delivery | One retry algorithm selected/swapped at runtime. |
| 14 | Strategy | Logistics optimization | Exactly one objective algorithm active per planning call. |
| 15 | Chain of Responsibility | Authentication risk | Sequential handlers with pass/handle; stop at first decisive decision. |
| 16 | Chain of Responsibility | Developer platform/PR gates | Runtime-assembled validation pipeline with short-circuit on first failure. |
| 17 | Command | Finance operations | Requests encapsulated as objects for queueing, history, undo, macro. |
| 18 | Command | Warehouse automation | Task objects queued/replayed/dry-run independent of receiver internals. |
| 19 | Template Method | Banking settlement | Fixed algorithm skeleton with overridable bank-specific steps. |
| 20 | Template Method | Compliance/data governance | Legally fixed purge sequence with hook-based variant steps. |
