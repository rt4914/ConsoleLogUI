# ConsoleLogUI

## Task
Build a UI component (Android) that shows logs on screen so you can debug while running code on the phone.

## Approach
As per this task definition the best end result should have following properties:
1. Whenever the developer use our custom logger it should write a log statement to a central storage.
2. The storage should persist all logs only for the current session and after that it should be cleared.
3. There should be screen which can be used from anywhere so that the developer can see all logs at once place.
4. The logs should be visible for multiple activities too and not just for single activity.
5. If the app is in release mode then all this code should not run, meaning, the app should not log to storage and also it should not show any UI for log list.

## Solution
1. Create `BaseActivity` which will be used by all activities. This `BaseActivity` should will be responsible for showing a button on all screens such that when we click it, it will open a new screen (DialogFragment) to show list of all logs.
2. Create a `ConsoleLogger` file so that we can implement all log functions and also write to a local internal file.
3. The `ConsoleLogger` file will also be responsible for clearing all logs at the start of app and read all logs when needed.


## Future Work
1. Inside `DialogFragment` use a `RecyclerView` to show all logs instead of `TextView`.
2. Each log UI should be split into multiple views with different color code for time, log-level and message.

## Resources

[Debug APK Link](shorturl.at/wBFJ6)
[Release APK Link](shorturl.at/gnBSZ)
