In order to understand this, you must first understand how BaseAdapter works, since CursorAdapter is a subclass of BaseAdapter.

Android maintains a pool of views for a ListView which it will give to you so you can reuse it instead of creating a new view each time.

In BaseAdapter, you will have a function called getView(), to which one of the parameters is a View object named convertView. Basically, this convertView will be null if the list is being loaded for the first time, and it will not be null once you start sliding the list. Therefore, in the getView() method of your BaseAdapter, you will check if convertView is null. If yes, you will inflate it. Then you can use the view and set its elements as normal. This will improve the scrolling performance of a listview tremendously.

A CursorAdapter makes it easy to use when the data source of a listview is a database. In a cursor adapter, however, Android takes care of checking whether the convertView is null or not. In the newView() method, you simply inflate the view and return it. In the bindView() method, you set the elements of your view.

As an example, imagine a listview on a device which can show upto 11 list items on the screen. In this case, newView() will be called upto 11 times. However, bindView() will be called many times whenever you scroll the list view. The 11 views you created in your newView method will be reused again and again as you scroll the list.
