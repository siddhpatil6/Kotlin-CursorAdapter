In order to understand this, you must first understand how BaseAdapter works, since CursorAdapter is a subclass of BaseAdapter.


Android maintains a pool of views for a ListView which it will give to you so you can reuse it instead of creating a new view each time.


In BaseAdapter, you will have a function called getView(), to which one of the parameters is a View object named convertView. Basically, this convertView will be null if the list is being loaded for the first time, and it will not be null once you start sliding the list. Therefore, in the getView() method of your BaseAdapter, you will check if convertView is null. If yes, you will inflate it. Then you can use the view and set its elements as normal. This will improve the scrolling performance of a listview tremendously.


A CursorAdapter makes it easy to use when the data source of a listview is a database. In a cursor adapter, however, Android takes care of checking whether the convertView is null or not. In the newView() method, you simply inflate the view and return it. In the bindView() method, you set the elements of your view.


As an example, imagine a listview on a device which can show upto 11 list items on the screen. In this case, newView() will be called upto 11 times. However, bindView() will be called many times whenever you scroll the list view. The 11 views you created in your newView method will be reused again and again as you scroll the list.


bind view method will be called multiple time and it sets data multiple time whenever we scroll listview
<br>

`override fun bindView(view: View?, p1: Context?, cursor: Cursor?){`

        var tvName=view?.findViewById<TextView>(R.id.tvName)
        var text=cursorz?.getColumnIndex(cursor?.getColumnName(1)!!)
        Log.d("kk-->>",text.toString())
        tvName?.text=cursor?.getString(cursor?.getColumnIndexOrThrow("NAME")!!)
`}`


<br>
it will be called upto the times number of items we have, suppose we have list of 11 items on screen. In this case it will be called 11 times and this view will be reused whenever we scroll the list.


`override fun newView(p0: Context?, p1: Cursor?, parent: ViewGroup?): View`
`{`

      return LayoutInflater.from(contextz).inflate(R.layout.cursor_row, parent, false);

`}`


The Best Part of Cursor Adapter is SwapCursor/ Change Cursor
Usually if we wants to fetch data from sqlite database we fire sqlquery and parse data and than set to previous list and we have to refresh our list every time but changeCursor and SwapCursor Make it more efficient by passing new cursor to previous cursor.

In Channge Cursor it swaps new cursor with old cursor and than closes old cursor.and than update listview, so dont use it with cursorLoader

// Switch to new cursor and update contents of ListView

`adapter.changeCursor(todoCursor);`

// Called when a new Loader needs to be created


`public void changeCursor(Cursor cursor)
{`
        
        Cursor old = swapCursor(cursor);
        
        if (old != null) {
            old.close();
        }
`}`


You don’t have to open and close the Cursor yourself, the loader will do this for you. This is the most important reason why you have to use swapCursor, it doesn’t close the Cursor when you swap it with another Cursor.

// Switch to new cursor and update contents of ListView

`adapter.swapCursor(todoCursor);`


`public Cursor swapCursor(Cursor newCursor){`

    if (newCursor == mCursor)
    {
       
         return null;
    
    }
    Cursor oldCursor = mCursor;
    if (oldCursor != null) {
        if (mChangeObserver != null) oldCursor.unregisterContentObserver(mChangeObserver);
        if (mDataSetObserver != null) oldCursor.unregisterDataSetObserver(mDataSetObserver);
    }
    mCursor = newCursor;
    if (newCursor != null) {
        if (mChangeObserver != null) newCursor.registerContentObserver(mChangeObserver);
        if (mDataSetObserver != null) newCursor.registerDataSetObserver(mDataSetObserver);
        mRowIDColumn = newCursor.getColumnIndexOrThrow("_id");
        mDataValid = true;
        // notify the observers about the new cursor
        notifyDataSetChanged();
    } else {
        mRowIDColumn = -1;
        mDataValid = false;
        // notify the observers about the lack of a data set
        notifyDataSetInvalidated();
    }
    return oldCursor;
`}`
