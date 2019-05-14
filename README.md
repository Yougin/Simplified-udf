# State mutation is inevitable, let's try to embrace it.
This is a simplified, but fully reactive, version of Unidirectional Data Flow concept.

There's a single faked web endpoint for fetching `Books`. These `Books` are being persisted in db.

`MVVM` is a chosen pattern for Presentation layer. `ViewModel` provided by the system is being used
because it survives configuration change.

`BooksActivity`, which is a `View` part of the pattern, gathers and exposes the stream of Ui Events,
called `Intents` (not to confuse with system's Intent).

`BooksViewModel` subscribes to the stream of `Intents` coming from the `View` (`BooksActivity`),
but instead of publishing the `Observable` for multicasting it, likewise it's done in a classic `MVI` approach, it
 uses pattern matching for executing different operations based on incoming `Intent`.

It's important to note that `Observable<ViewState>`, which is the only API `ViewModel` exposes,
happens
under control from a single place. Thanks to `BehaviorSubject`, the last ViewState is emitted and rendered after configuration change.

On one hand, `BooksViewModel` observes `Intent`ions from `View`, on the other hand it listens to
changes which are coming from the data layer.

`BooksRepository` exposes an observable data stream which emits every time the data set changed.
`Room` database is being used as a local datasource because of its ability to notify observers about data change event.

You can see `GetBooks` and `RefreshBooks` use cases in domain layer. Please note that `GetBooks` is an
`Observable` which emits upon *every* data change event, and `RefreshBooks` is `Completable`, which
completes right after data has been fetched and persisted.

The local database in its order emits an event about data set change to which we already subscribed using `GetBooks`.

The demo leverages product flavors to use DebugView only accessible in `internalDebug` build variant
. You can switch between Books sorting from it.

Until we have a robust way to inject functions, object "nonsense" is being used for Use Cases.
Though you may see a some of pure functions used for data processing - `Converters.kt`,
`groupByAlphabet()`, `groupByDate()`.

`Mocks` are still used for this example, along with `Fakes` for `Dao` and `Repository`. Fakes is the
 way to go though.
 
 Some other cool tricks are being used in this example, like a composable adapter, dagger 2 (not a 
 pro - still running on Dagger 1), inline classes (to fight with primitives obsession), typealiases for 
 better readability, `Arrow` library for an excellent `Option` implementation and much more.
 
 This is what it looks like:
 https://youtu.be/FgYI-BNsxSk
