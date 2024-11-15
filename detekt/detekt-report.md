# detekt

## Metrics

* 12 number of properties

* 5 number of functions

* 2 number of classes

* 2 number of packages

* 5 number of kt files

## Complexity Report

* 194 lines of code (loc)

* 159 source lines of code (sloc)

* 98 logical lines of code (lloc)

* 32 comment lines of code (cloc)

* 10 cyclomatic complexity (mcc)

* 5 cognitive complexity

* 4 number of total code smells

* 20% comment source ratio

* 102 mcc per 1,000 lloc

* 40 code smells per 1,000 lloc

## Findings (4)

### naming, FunctionMaxLength (1)

Function names should not be longer than the maximum set in the project configuration.

[Documentation](https://detekt.dev/docs/rules/naming#functionmaxlength)

* src/main/java/com/demo/advancedmultimodulearchitecture/ui/theme/Theme.kt:36:5
```
Function names should be at most 30 characters long.
```
```kotlin
33 )
34 
35 @Composable
36 fun AdvancedMultiModuleArchitectureTheme(
!!     ^ error
37   darkTheme: Boolean = isSystemInDarkTheme(),
38   // Dynamic color is available on Android 12+
39   dynamicColor: Boolean = true,

```

### naming, FunctionNaming (3)

Function names should follow the naming convention set in the configuration.

[Documentation](https://detekt.dev/docs/rules/naming#functionnaming)

* src/main/java/com/demo/advancedmultimodulearchitecture/MainActivity.kt:40:5
```
Function names should match the pattern: ([a-z][a-zA-Z0-9]*)|(`.*`)
```
```kotlin
37 }
38 
39 @Composable
40 fun Greeting(name: String, modifier: Modifier = Modifier) {
!!     ^ error
41   Column {
42     Text(
43       text = "Hello $name!",

```

* src/main/java/com/demo/advancedmultimodulearchitecture/MainActivity.kt:67:5
```
Function names should match the pattern: ([a-z][a-zA-Z0-9]*)|(`.*`)
```
```kotlin
64 
65 @Preview(showBackground = true)
66 @Composable
67 fun GreetingPreview() {
!!     ^ error
68   AdvancedMultiModuleArchitectureTheme {
69     Greeting("Android")
70   }

```

* src/main/java/com/demo/advancedmultimodulearchitecture/ui/theme/Theme.kt:36:5
```
Function names should match the pattern: ([a-z][a-zA-Z0-9]*)|(`.*`)
```
```kotlin
33 )
34 
35 @Composable
36 fun AdvancedMultiModuleArchitectureTheme(
!!     ^ error
37   darkTheme: Boolean = isSystemInDarkTheme(),
38   // Dynamic color is available on Android 12+
39   dynamicColor: Boolean = true,

```

generated with [detekt version 1.23.3](https://detekt.dev/) on 2024-11-15 19:38:52 UTC
