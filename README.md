# Animator helpers for Android and Kotlin


## Installation
``` gradle
implementation 'com.francescozoccheddu.animatorhelpers:KotlinAnimatorHelpers:0.1.0'
```


## Examples

### Delegated property
Setup
``` kotlin
var myAnimatedFloat by ABFloat(0f).apply {
    onUpdate = {
        println("myAnimatedFloat=$it")
    }
    speed = 3f
}
```
Change value
``` kotlin
myAnimatedFloat = 8f
```

### Standalone object
Setup
``` kotlin
val myAnimatedColor = ABColor(Color.RED).apply {
    onUpdate = {
        println("myAnimatedColor=#${Integer.toHexString(it)}")
    }
    duration = 0.5f
}
```
Change value
``` kotlin
myAnimatedColor.value = Color.YELLOW
```


## Classes
- *`AnimatedValue`*
    - `UnanimatedValue`
    - *`TargetedValue`*
        - *`ABValue`*
            - `ABColor`
            - `ABFloat`
            - `ABInt`
        - *`SmoothValue`*
            - `SmoothColor`
            - `SmoothFloat`
            - `SmoothInt`

### `AnimatedValue<Type>`
Base class
- **`value`** : `Type`  
`get` animated value / `set` target value
- **`running`** : `Boolean`  
`get` whether animation is running or finished
- **`onUpdate`** : `((Type) -> Unit)?`  
`get` / `set` value update callback
- **`reach()`** : `Unit`  
end animation and instantly jump to target value

### `TargetedValue<Type>`
Animated value with target
- **`target`**  
`get` target value (`set` by **`value`** property)

### `ABValue<Type>`
Android `ValueAnimator` wrapper  
Use it for transitions or uninterruptible animations
- **`interpolator`** : `TimeInterpolator`  
`get` / `set` value easing
- **`durationMode`** : `ABValue.DurationMode`  
`get` duration mode (`set` by **`speed`** or **`duration`** properties)
- **`duration`** : `Float`  
`get` / `set` constant animation duration (seconds)
- **`speed`** : `Float`  
`get` / `set` constant animation speed (amount per second)

### `SmoothValue<Type>`
Continuous smoother  
Use it for continuously changing target values (eg. to smooth touch drag gestures)
- **`smoothing`** : `Float`  
`get` / `set` smoothness amount (`0.2f` is a reasonable value)