# Interview Pattern Scenarios — Answer Key

## Scenario 1 — Unified KYC Verification Across Legacy Vendors
1. **Correct pattern name:** Adapter.
2. **Best fit:** The core issue is incompatible third-party contracts; adapters convert vendor-specific request/response/error shapes into one internal KYC contract.
3. **Why alternatives are not right:** `Facade` simplifies subsystem usage but does not inherently translate incompatible interfaces; `Strategy` swaps algorithms behind one already-compatible interface, while here compatibility itself is missing.
4. **Core Java design sketch:** `KycProvider` (target interface), `NormalizedKycRequest`, `KycDecision`; `AadhaarAdapter`, `RegCheckAdapter`, `GovKycAdapter`; `OnboardingService` depends only on `KycProvider`.
5. **Step-by-step implementation approach:**  
   1. Define internal request/response model.  
   2. Define `KycProvider#verify(NormalizedKycRequest)`.  
   3. Implement one adapter per SDK with full mapping/conversion.  
   4. Inject adapter into onboarding flow via config/factory.  
   5. Add tests for status/error mapping parity.
6. **Interview follow-up traps + ideal responses:** “Where should mapping live?” → inside adapter, never caller; “What if vendor adds new status?” → map in that adapter only, keep internal enum stable.
7. **Common mistakes + corrections:** Leaking vendor DTO in service layer → replace with normalized model; central `if(vendor)` logic → move selection to factory and keep behavior in adapter classes.
8. **Extensibility and trade-offs:** Very extensible for new vendors; trade-off is extra classes and mapping maintenance, but blast radius stays small.
9. **Optional mini UML/pseudocode:**  
   `OnboardingService -> KycProvider <- AadhaarAdapter/RegCheckAdapter`  
   `decision = provider.verify(normalizedReq);`

## Scenario 2 — Common Signed URL Generator for Multi-Cloud Storage
1. **Correct pattern name:** Adapter.
2. **Best fit:** S3/Azure/MinIO SDKs expose incompatible APIs and units; adapters normalize invocation and output to one signed-URL contract.
3. **Why alternatives are not right:** `Strategy` assumes callers already have interchangeable implementations; here implementations are not interchangeable until adapted. `Proxy` keeps same API but controls access/caching/lazy calls, not API-shape translation.
4. **Core Java design sketch:** `SignedUrlProvider` interface; `SignedUrlRequest`/`SignedUrlResult`; `S3SignedUrlAdapter`, `AzureSignedUrlAdapter`, `MinioSignedUrlAdapter`; selector factory by provider key.
5. **Step-by-step implementation approach:** define target contract, implement per-provider conversion (TTL units, method enums, path style), normalize outputs, plug adapters into provider registry.
6. **Interview follow-up traps + ideal responses:** “Can we add provider fields to common interface?” → avoid unless truly common; keep target contract minimal and stable.
7. **Common mistakes + corrections:** Conversion in caller loops → move into adapters; returning raw SDK objects → return normalized result DTO.
8. **Extensibility and trade-offs:** Easy GCS addition via new adapter; trade-off is keeping compatibility tests when SDK versions change.
9. **Optional mini UML/pseudocode:**  
   `DownloadService -> SignedUrlProvider`  
   `provider.generate(req)` delegates to cloud SDK with mapped parameters.

## Scenario 3 — Fraud Model Serving with Lazy Load + Access Gate + Audit
1. **Correct pattern name:** Proxy.
2. **Best fit:** Same `FraudScorer` interface is preserved while wrappers control creation timing, authorization, and audit around the real scorer.
3. **Why alternatives are not right:** `Decorator` is for additive domain capability; here capability (`score`) stays same and access/timing are controlled. `Facade` gives a simplified API over many subsystems, not controlled substitution for one expensive subject.
4. **Core Java design sketch:** `FraudScorer`; `RealFraudScorer`; proxies `LazyFraudScorerProxy`, `AuthFraudScorerProxy`, `AuditFraudScorerProxy`; each proxy holds `FraudScorer delegate`.
5. **Step-by-step implementation approach:** implement real scorer, add lazy proxy (instantiate on first call), add auth proxy (pre-check), add audit proxy (post-success log), compose wrappers in desired order.
6. **Interview follow-up traps + ideal responses:** “Why interface-typed delegate?” → enables proxy stacking; “Auth before lazy?” → yes to avoid expensive load for denied users.
7. **Common mistakes + corrections:** Creating real scorer in proxy constructor → defeats virtual proxy; logging all attempts when requirement says successful only → log after successful delegate call.
8. **Extensibility and trade-offs:** New control concerns (rate limit, circuit breaker) can be added as new proxies; trade-off is wrapper-order complexity.
9. **Optional mini UML/pseudocode:**  
   `Client -> AuditProxy -> AuthProxy -> LazyProxy -> RealFraudScorer`  
   `if (!authorized) throw; ensureReal(); score = delegate.score(txn); audit(score);`

## Scenario 4 — Profile Repository with Read Cache and Write Protection
1. **Correct pattern name:** Proxy.
2. **Best fit:** Callers already use `CustomerProfileRepository`; proxy keeps that interface and transparently enforces read cache + write restrictions.
3. **Why alternatives are not right:** `Decorator` adds business behavior to object semantics; this scenario is primarily control/optimization around existing operations. `Adapter` is unnecessary because interface incompatibility is absent.
4. **Core Java design sketch:** `CustomerProfileRepository`; `RemoteProfileRepository`; `CachedReadOnlyProfileRepositoryProxy` with TTL cache and role context.
5. **Step-by-step implementation approach:** preserve repository contract, implement cache lookup/miss delegation for reads, block writes by policy, wire proxy where recommendation module gets repository.
6. **Interview follow-up traps + ideal responses:** “Where cache invalidation?” → inside proxy policy (TTL/manual invalidate API), not business service.
7. **Common mistakes + corrections:** Exposing cache map externally → keep private; bypassing proxy for writes accidentally → inject interface dependency consistently.
8. **Extensibility and trade-offs:** Can split into composable proxies (cache proxy + auth proxy); trade-off is additional indirection and cache consistency concerns.
9. **Optional mini UML/pseudocode:**  
   `RecommendationService -> CustomerProfileRepository <- CachedReadOnlyProxy -> RemoteRepo`

## Scenario 5 — One-Click Production Rollback Orchestrator
1. **Correct pattern name:** Facade.
2. **Best fit:** Multiple callers need one high-level rollback API hiding orchestration complexity across many subsystems.
3. **Why alternatives are not right:** `Template Method` is for subclass-controlled variation of one algorithm skeleton; this asks for simplification, not inheritance customization. `Chain of Responsibility` short-circuits on handlers, but rollback requires explicit ordered execution of all steps.
4. **Core Java design sketch:** `RollbackFacade` composing `TrafficService`, `FlagService`, `RolloutService`, `DbCompatibilityService`, `CacheService`, `IncidentLogService`.
5. **Step-by-step implementation approach:** define facade request/result DTOs, compose subsystems, centralize ordered calls in one method, expose facade to CLI/API callers, keep subsystems independently injectable.
6. **Interview follow-up traps + ideal responses:** “Does facade hide subsystem APIs?” → no, they can remain public; facade is convenience + orchestration.
7. **Common mistakes + corrections:** Duplicating rollback sequence across controllers → route all through facade; putting unrelated business policies in callers → move to facade orchestration layer.
8. **Extensibility and trade-offs:** New step added once in facade; trade-off is facade can become god-class if too many unrelated workflows are stuffed into one class.
9. **Optional mini UML/pseudocode:**  
   `CLI/API -> RollbackFacade -> {Traffic,Flags,Rollout,DB,Cache,Incident}`  
   `rollback() { freeze(); revertFlags(); rollbackDeploy(); ... }`

## Scenario 6 — Merchant Onboarding Pipeline Simplification
1. **Correct pattern name:** Facade.
2. **Best fit:** Two caller types need the same ordered onboarding workflow behind one simple method.
3. **Why alternatives are not right:** `Adapter` translates incompatible APIs, which is not the central requirement. `Observer` broadcasts events to many listeners without strong orchestration order guarantees.
4. **Core Java design sketch:** `MerchantOnboardingFacade` + composed services (`OcrService`, `SanctionsService`, `BankVerifier`, `LedgerService`, `Notifier`), returning `OnboardingResult`.
5. **Step-by-step implementation approach:** define facade input/result, map ordered steps, manage error propagation and partial statuses in facade, update callers to use facade entrypoint.
6. **Interview follow-up traps + ideal responses:** “Could this be event-driven?” → possible architecture choice, but requirement prioritizes deterministic ordered orchestration from one call.
7. **Common mistakes + corrections:** Embedding orchestration in controller and batch job separately → centralize in facade; leaking subsystem DTOs out of facade → return consolidated DTO.
8. **Extensibility and trade-offs:** Easier to add GST step centrally; trade-off is facade method can grow, so break internals into private helper methods/use-case classes as needed.
9. **Optional mini UML/pseudocode:**  
   `AdminPortal/BulkJob -> MerchantOnboardingFacade.onboard(req)`

## Scenario 7 — Export Payload Add-ons (Compression, Encryption, Signature)
1. **Correct pattern name:** Decorator.
2. **Best fit:** Optional, stackable behavior layers must augment payload generation dynamically without combination-class explosion.
3. **Why alternatives are not right:** `Proxy` focuses on access/timing/control of same capability; here wrappers add net new payload transformations. `Chain of Responsibility` passes request through handlers usually with pass/fail semantics, not persistent wrapped object augmentation.
4. **Core Java design sketch:** `PayloadRenderer` interface; `BasePayloadRenderer`; abstract `PayloadDecorator` holding `PayloadRenderer`; concrete `GzipDecorator`, `AesDecorator`, `SignatureDecorator`.
5. **Step-by-step implementation approach:** define base renderer, implement decorator base class, add concrete wrappers applying transform around delegated bytes, compose per customer contract.
6. **Interview follow-up traps + ideal responses:** “Where should transform order live?” → composition/wiring layer; order affects output and should be explicit.
7. **Common mistakes + corrections:** Maintaining separate mutable “applied options list” and payload bytes independently → derive output through delegation chain only.
8. **Extensibility and trade-offs:** New transformation is a new decorator class; trade-off is debug complexity due to deep wrapper chains.
9. **Optional mini UML/pseudocode:**  
   `renderer = new SignatureDecorator(new AesDecorator(new GzipDecorator(new BasePayloadRenderer())))`

## Scenario 8 — Search Ranking Boost Layers
1. **Correct pattern name:** Decorator.
2. **Best fit:** Base scorer must be incrementally enhanced by independent, stackable boost layers that each add score/explanation.
3. **Why alternatives are not right:** `Strategy` picks one algorithm at a time; boosts need combined execution. `Composite` models part-whole trees; boost wrappers are sequential augmenters over one scorer, not child aggregates.
4. **Core Java design sketch:** `RankingScorer` returns `ScoreResult(score, explanation)`; `BaseScorer`; abstract `ScoreDecorator`; `FreshnessBoost`, `InventoryBoost`, `PremiumSellerBoost`, `GeoBoost`.
5. **Step-by-step implementation approach:** create score DTO, implement base scorer, implement each boost decorator adding delta and explanation suffix, wire boost chain per request context.
6. **Interview follow-up traps + ideal responses:** “What if boost should be optional?” → omit that decorator in composition; no base code change.
7. **Common mistakes + corrections:** One scorer with many boolean flags → split into decorators; mutating shared explanation state externally → compose explanation in returned DTO.
8. **Extensibility and trade-offs:** Very extensible for ranking experiments; trade-off is too many tiny decorators can make performance tracing harder.
9. **Optional mini UML/pseudocode:**  
   `scorer = new GeoBoost(new FreshnessBoost(new BaseScorer())); result = scorer.score(ctx);`

## Scenario 9 — Subscription Billing Lifecycle
1. **Correct pattern name:** State.
2. **Best fit:** Legal actions and transitions depend on current lifecycle stage, and transition authority belongs inside state objects.
3. **Why alternatives are not right:** `Strategy` is caller-selected behavior swapping, while here state progression is object-driven. `Chain of Responsibility` is handler delegation flow, not lifecycle transition modeling.
4. **Core Java design sketch:** `SubscriptionContext` with `SubscriptionState current`; `SubscriptionState` interface/abstract class with default rejection; concrete states `TrialState`, `ActiveState`, `GraceState`, `SuspendedState`, `CancelledState`.
5. **Step-by-step implementation approach:** define actions, add reject-by-default base state, implement valid transitions in each concrete state, keep context methods as pure delegation.
6. **Interview follow-up traps + ideal responses:** “Who decides next state?” → current state object; “How to add PAUSED?” → add `PausedState` and adjust only relevant transitions.
7. **Common mistakes + corrections:** Giant enum switch in context → replace with state polymorphism; silent invalid actions → throw explicit state/action exception.
8. **Extensibility and trade-offs:** Good maintainability for complex lifecycles; trade-off is more classes.
9. **Optional mini UML/pseudocode:**  
   `context.renew() -> currentState.renew(context)`; state may call `context.setState(new ActiveState())`.

## Scenario 10 — Progressive Deployment Lifecycle Controller
1. **Correct pattern name:** State.
2. **Best fit:** Deployment commands are valid/invalid depending on phase; phase objects encapsulate allowed actions and transitions.
3. **Why alternatives are not right:** `Strategy` selects one algorithm, not mutable lifecycle transitions. `Template Method` enforces fixed step order in one run, while deployments can pause/resume/rollback across evolving states.
4. **Core Java design sketch:** `DeploymentContext`; `DeploymentState` contract; concrete states `Draft`, `Approved`, `Canary`, `FullRollout`, `Paused`, `RolledBack`, `Completed`.
5. **Step-by-step implementation approach:** implement action methods in state interface, default-reject in abstract base, override allowed methods per state, delegate from context.
6. **Interview follow-up traps + ideal responses:** “Where to keep transition graph?” → implicitly in state class methods; avoid central transition matrix duplication.
7. **Common mistakes + corrections:** boolean flags like `isCanary/isPaused` on one class → replace with one `currentState` polymorphic model.
8. **Extensibility and trade-offs:** New `VerificationState` localizes changes; trade-off is transition path reasoning needs tests/documentation.
9. **Optional mini UML/pseudocode:**  
   `CanaryState.promote(ctx) => ctx.setState(new FullRolloutState());`

## Scenario 11 — Feature Flag Change Broadcasting
1. **Correct pattern name:** Observer.
2. **Best fit:** One subject state change (flag update) must notify multiple independent listeners.
3. **Why alternatives are not right:** `Chain of Responsibility` stops at handler decision, but all listeners must run. `Strategy` is one selected algorithm, not many simultaneous reactions.
4. **Core Java design sketch:** `FlagStore` (subject) with `register/unregister/notify`; `FlagListener` interface; concrete listeners `CacheInvalidator`, `WsPusher`, `AuditEmitter`, `ExperimentMetricsListener`.
5. **Step-by-step implementation approach:** define event DTO, implement listener abstraction, keep listener list in subject, iterate listeners with per-listener exception isolation.
6. **Interview follow-up traps + ideal responses:** “Should listener failure stop publish?” → no, isolate and continue; “Can listener be dynamic?” → yes via runtime registration.
7. **Common mistakes + corrections:** Direct concrete service calls in `updateFlag` → replace with observer list; wrapping whole loop in one try/catch → move try/catch inside loop.
8. **Extensibility and trade-offs:** Easy to add listeners; trade-off is possible event storms and ordering concerns if synchronous.
9. **Optional mini UML/pseudocode:**  
   `FlagStore.notify(flagEvent) -> for listener in listeners: listener.onFlagChanged(event)`

## Scenario 12 — IoT Telemetry Event Fan-Out
1. **Correct pattern name:** Observer.
2. **Best fit:** Telemetry publisher broadcasts events to multiple decoupled subscribers that evolve independently.
3. **Why alternatives are not right:** `Command` encapsulates executable requests for later execution/replay; this scenario is one-to-many event notification. `Chain of Responsibility` is first-handler or sequential pass flow, not full fan-out.
4. **Core Java design sketch:** `TelemetryPublisher`, `TelemetryObserver`, event model `TelemetryEvent`; subscribers `AlertObserver`, `IncidentObserver`, `RemediationObserver`, `AnalyticsObserver`.
5. **Step-by-step implementation approach:** define observer contract, register subscribers, publish relevant events, isolate failures, optionally add async dispatch wrapper later.
6. **Interview follow-up traps + ideal responses:** “Need event filtering?” → observer can inspect event type or subject can route by topic maps.
7. **Common mistakes + corrections:** Coupling publisher to concrete tools (Slack/Jira/etc.) → depend on observer interface only.
8. **Extensibility and trade-offs:** Subscriber ecosystem scales well; trade-off is harder end-to-end tracing unless event IDs/log correlation are enforced.
9. **Optional mini UML/pseudocode:**  
   `publisher.publish(event); observers.forEach(o -> o.onTelemetry(event));`

## Scenario 13 — Webhook Retry Policy Per Merchant SLA
1. **Correct pattern name:** Strategy.
2. **Best fit:** Delivery service needs one interchangeable retry algorithm selected externally at runtime.
3. **Why alternatives are not right:** `State` implies object-driven transition among behavior states; here selection is config-driven by merchant. `Template Method` is fixed skeleton with subclass hooks, but retry policies are independent alternatives.
4. **Core Java design sketch:** `RetryPolicy` interface (`nextDelay(attempt, context)`); concrete `NoRetryPolicy`, `FixedIntervalPolicy`, `ExponentialJitterPolicy`; `WebhookDeliveryService` holds `RetryPolicy`.
5. **Step-by-step implementation approach:** define policy contract, implement algorithms, inject policy based on merchant config, keep delivery loop generic.
6. **Interview follow-up traps + ideal responses:** “How to A/B test policy?” → swap strategy via config/experiment assignment without service code edits.
7. **Common mistakes + corrections:** switch by policy type in delivery service → polymorphic call; mixing delay computation with HTTP send logic → keep separated.
8. **Extensibility and trade-offs:** Fast addition of new policies; trade-off is external selector complexity and config correctness.
9. **Optional mini UML/pseudocode:**  
   `while(fail && policy.shouldRetry(attempt)){ sleep(policy.nextDelay(...)); }`

## Scenario 14 — Delivery Route Objective Selection
1. **Correct pattern name:** Strategy.
2. **Best fit:** Exactly one route objective (fastest/cheapest/greenest) should be chosen per plan request.
3. **Why alternatives are not right:** `State` would imply planner self-transitions through lifecycle phases; here objective is externally selected. `Decorator` would execute additive layers, but objectives are mutually exclusive alternatives.
4. **Core Java design sketch:** `RouteObjectiveStrategy` with `computeRoute(graph, request)`; concrete strategies `FastestRoute`, `CheapestRoute`, `GreenRoute`; `RoutePlanner` context uses chosen strategy.
5. **Step-by-step implementation approach:** define route contract/DTO, implement each algorithm class, wire strategy selector from order metadata/experiments, keep planner free of objective branches.
6. **Interview follow-up traps + ideal responses:** “Can planner switch strategy at runtime?” → yes via setter/injection; “Can combine objectives?” → then define a new strategy or weighted strategy, still one active algorithm object.
7. **Common mistakes + corrections:** embedding all objective branches in one class → split into strategy classes; treating objectives as decorators → avoid since not cumulative by requirement.
8. **Extensibility and trade-offs:** New objective class is isolated; trade-off is needing robust selection logic and algorithm performance benchmarking.
9. **Optional mini UML/pseudocode:**  
   `planner.setStrategy(new FastestRoute()); route = planner.plan(req);`

## Scenario 15 — Login Risk Decision Pipeline
1. **Correct pattern name:** Chain of Responsibility.
2. **Best fit:** Request flows through ordered risk handlers where each can decide or pass onward; processing stops at first decisive result.
3. **Why alternatives are not right:** `Observer` notifies all listeners and does not short-circuit by decision. `Strategy` chooses one algorithm up front, but this requires multi-step progressive evaluation.
4. **Core Java design sketch:** `RiskHandler` base class with `setNext`; decision enum `ALLOW/DENY/PASS`; handlers `IpReputationHandler`, `DeviceTrustHandler`, `VelocityHandler`, `StepUpHandler`; terminal fallback handler.
5. **Step-by-step implementation approach:** define decision model, create abstract forwarding handler, implement rule per handler, wire chain by configuration, run request from head.
6. **Interview follow-up traps + ideal responses:** “How to reorder checks?” → chain wiring outside handlers; “What if none decides?” → explicit terminal fallback.
7. **Common mistakes + corrections:** each handler knowing full pipeline rules → isolate to own rule + next delegate only.
8. **Extensibility and trade-offs:** New checks are easy to insert; trade-off is debugging path visibility without structured trace logs.
9. **Optional mini UML/pseudocode:**  
   `head.handle(req){ d=evaluate(req); return d==PASS ? next.handle(req) : d; }`

## Scenario 16 — Pull Request Merge-Gate Validation Chain
1. **Correct pattern name:** Chain of Responsibility.
2. **Best fit:** Validators should be linked dynamically and stop on first failure with reason.
3. **Why alternatives are not right:** `Facade` would orchestrate all checks but usually runs all steps, while requirement asks short-circuit. `Template Method` fixes sequence in inheritance hierarchy and is less flexible for per-repo runtime reordering.
4. **Core Java design sketch:** `PrValidator` handler base, `ValidationResult`, concrete handlers (`TitleValidator`, `TicketLinkValidator`, `TestStatusValidator`, `LicenseValidator`, `SecurityPolicyValidator`), chain builder from repo config.
5. **Step-by-step implementation approach:** create result object, implement forwarding base, encode one responsibility per validator, externalize chain assembly, add fallback validator.
6. **Interview follow-up traps + ideal responses:** “Can one validator call all others?” → no, violates separation; chain owns progression.
7. **Common mistakes + corrections:** static singleton chain hardcoded globally → build chain from repo policy; returning boolean only → return rich failure reason DTO.
8. **Extensibility and trade-offs:** Per-repo customization is strong; trade-off is many small classes and ordering management.
9. **Optional mini UML/pseudocode:**  
   `result = chainHead.validate(pr); if(!result.ok) blockMerge(result.reason);`

## Scenario 17 — Payout Operation Queue with Undo/Compensation
1. **Correct pattern name:** Command.
2. **Best fit:** Operations are first-class request objects that can be queued, audited, replayed, and compensated.
3. **Why alternatives are not right:** `Strategy` chooses one algorithm, but here many operation requests are executed over time. `Chain of Responsibility` forwards one request among handlers; this scenario needs explicit queued execution sequence.
4. **Core Java design sketch:** `PayoutCommand` (`execute`, `undo`, `isUndoSupported`); concrete commands (`ReserveFundsCommand`, `DebitCommand`, `MarkPaidCommand`, `NotifyCommand`); `PayoutInvoker` with queue/history; `BatchPayoutCommand` as macro.
5. **Step-by-step implementation approach:** define command interface, implement receiver interactions in commands, invoker executes from queue and records history, apply compensation on failure.
6. **Interview follow-up traps + ideal responses:** “Where does undo state live?” → inside command instance; “How macro works?” → composite command executing children and undoing in reverse.
7. **Common mistakes + corrections:** receiver logic in invoker switches → move to command classes; global undo logic with no per-command context → store reversal data in command instance.
8. **Extensibility and trade-offs:** New operation type is new command class; trade-off is object count and command persistence/versioning complexity.
9. **Optional mini UML/pseudocode:**  
   `invoker.enqueue(cmd); invoker.run(); onError -> history.pop().undo();`

## Scenario 18 — Warehouse Robot Task Sequencer
1. **Correct pattern name:** Command.
2. **Best fit:** Robot tasks should be encapsulated as task objects for queueing, replay, and dry-run without changing producers.
3. **Why alternatives are not right:** `Observer` broadcasts events to listeners, but here ordered execution and replay are core. `Template Method` fixes one algorithm skeleton; robot tasks are independent invocable requests.
4. **Core Java design sketch:** `RobotCommand` (`execute`, optional `undo`, `simulate`); commands `MoveToCommand`, `PickCommand`, `DropCommand`, `DockChargeCommand`; `RobotInvoker` queue with modes (`LIVE`, `DRY_RUN`); receiver `RobotController`.
5. **Step-by-step implementation approach:** define command contract, implement command-to-receiver mapping, add invoker queue/replay methods, add dry-run path calling `simulate`.
6. **Interview follow-up traps + ideal responses:** “How to replay failed batch?” → persist command list + deterministic order and re-run through invoker.
7. **Common mistakes + corrections:** upstream systems invoking robot APIs directly → force through command queue; hardcoded command `instanceof` in invoker → use polymorphism.
8. **Extensibility and trade-offs:** New robot action requires only new command; trade-off is command serialization compatibility over time.
9. **Optional mini UML/pseudocode:**  
   `for(cmd: queue) mode==DRY_RUN ? cmd.simulate() : cmd.execute();`

## Scenario 19 — Bank Settlement Processor Skeleton
1. **Correct pattern name:** Template Method.
2. **Best fit:** Settlement run has invariant ordered skeleton with bank-specific variability only in selected steps.
3. **Why alternatives are not right:** `Strategy` swaps entire algorithms and does not enforce shared sequence centrally. `Facade` simplifies subsystem orchestration but does not provide inheritance-based step overridability with fixed template enforcement.
4. **Core Java design sketch:** abstract `SettlementProcessor` with final `processSettlement()`, concrete non-overridable shared steps, abstract `validateBatch`/`applyEntries`, optional `postSettlementArchive` hook; concrete subclasses per bank.
5. **Step-by-step implementation approach:** implement final template sequence, identify invariant vs variable steps, code hooks with defaults, create bank subclasses overriding only variable hooks.
6. **Interview follow-up traps + ideal responses:** “Why final template method?” → prevents order breakage and mandatory-step skipping.
7. **Common mistakes + corrections:** making template method overrideable → mark final; stuffing all variations into `if(bankType)` → move into subclasses.
8. **Extensibility and trade-offs:** Clear control over compliance-critical order; trade-off is subclass proliferation if too many variants.
9. **Optional mini UML/pseudocode:**  
   `final processSettlement(){ lock(); load(); validate(); settle(); hook(); publish(); unlock(); }`

## Scenario 20 — Data Retention Purge Workflow
1. **Correct pattern name:** Template Method.
2. **Best fit:** Purge has legally mandated fixed sequence, while product-specific deletion/notification details vary.
3. **Why alternatives are not right:** `Chain of Responsibility` allows pass/stop dynamics and potentially skipped order, risky for legal workflows. `State` models evolving object phases, not one deterministic operation skeleton.
4. **Core Java design sketch:** abstract `DataPurgeWorkflow` with final `runPurge()`, invariant methods (`authorize`, `snapshot`, `emitAudit`), abstract methods (`deletePrimary`, `deleteSecondary`, `notifyRequester`), optional `postPurgeWarmup` hook.
5. **Step-by-step implementation approach:** codify mandatory order in final method, lock invariants as final/private, expose variant points via abstract/hook methods, subclass per product vertical.
6. **Interview follow-up traps + ideal responses:** “Where to enforce audit always runs?” → inside template method after delete steps with guaranteed call position.
7. **Common mistakes + corrections:** representing purge path as flags and conditionals in one class → move to template + subclasses; skipping audit on one subtype → impossible when audit step is invariant in template.
8. **Extensibility and trade-offs:** Easy onboarding of new verticals with constrained customization; trade-off is inheritance rigidity if variation becomes too deep.
9. **Optional mini UML/pseudocode:**  
   `final runPurge(){ authorize(); snapshot(); deletePrimary(); deleteSecondary(); audit(); notify(); hook(); }`

---

## Final Validation Summary

| Scenario | Pattern | Domain | Primary discriminator |
|---|---|---|---|
| 1 | Adapter | Fintech compliance | Translate incompatible KYC SDK contracts to one internal interface. |
| 2 | Adapter | Cloud storage platform | Normalize multi-cloud signed URL API/units to one target contract. |
| 3 | Proxy | ML fraud platform | Control access/lazy-load/audit around same scorer capability. |
| 4 | Proxy | Customer profile backend | Preserve repository API while adding cache/protection controls. |
| 5 | Facade | DevOps rollback operations | One high-level orchestration call over many subsystem steps. |
| 6 | Facade | Merchant onboarding | Centralize ordered subsystem workflow for multiple callers. |
| 7 | Decorator | Secure payload processing | Stack additive transformations by wrapping same interface. |
| 8 | Decorator | Search ranking | Incremental score/explanation augmentation via wrappers. |
| 9 | State | SaaS subscription lifecycle | Behavior and transitions depend on current internal state object. |
| 10 | State | Progressive deployment lifecycle | Stage-specific legal actions and transitions are state-owned. |
| 11 | Observer | Feature flag platform | One subject update must notify all registered listeners. |
| 12 | Observer | IoT operations | Telemetry fan-out to many independent subscribers. |
| 13 | Strategy | Webhook reliability | Exactly one retry algorithm chosen/swapped at runtime. |
| 14 | Strategy | Logistics routing | Exactly one optimization objective strategy per request. |
| 15 | Chain of Responsibility | Auth risk pipeline | Ordered handlers decide/pass; first decisive result stops flow. |
| 16 | Chain of Responsibility | PR governance | Runtime-configured validators short-circuit on first failure. |
| 17 | Command | Payout operations | Requests as objects enable queueing, history, and compensation. |
| 18 | Command | Warehouse robotics | Task objects support queued execution, replay, and dry-run. |
| 19 | Template Method | Bank settlement | Final skeleton enforces mandatory order with overridable steps. |
| 20 | Template Method | Data governance/compliance | Legally fixed purge workflow with controlled extension hooks. |
