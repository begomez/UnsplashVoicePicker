README
------

Unsplash image picker for Android allowing search using voice commands (en).

Clean architecture separation with domain, data, remote, presentation and UI layers.

Shared data models between layers.

HOW TO USE
----------

1- Add unsplash token in SecInterceptor class

    private interface Credentials {
        companion object {
            //TODO: Unsplash API token goes here...
            val TOKEN = ""
        }
    }

2- Launch explicit Intent to search pics:

    val intent = Intent(this, SearchActivity::class.java)

    this.startActivityForResult(intent, Constants.RequestCodes.REQUEST_PICKER)

3- Perform search and scroll through results. Single click selects an item, long click open it on detail view.

4- Retrieve results overriding onActivityResult()

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        when (requestCode) {
            Constants.RequestCodes.REQUEST_PICKER -> {
                if (resultCode == Activity.RESULT_OK) {
                    val pic : PicData? = data?.extras?.getParcelable(Constants.ParamNames.PARAM_IMAGE_SELECTED)
                }
            }

See sample app for full example