package dev.aleksejgolikov.espresso.actions

fun bannerMustShow(viewId: Int) {
    viewById(viewId).waitDisplayed()
}

fun viewMustHaveText(
    viewId: Int,
    text: String,
) {
    viewById(viewId).waitText(text)
}
