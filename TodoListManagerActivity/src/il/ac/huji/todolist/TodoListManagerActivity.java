package il.ac.huji.todolist;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public class TodoListManagerActivity extends Activity {

    private static final int CONTEXT_MENU_EDIT_ITEM = 0;
	private static final int CONTEXT_MENU_CALL_ITEM = 0;
	private ArrayAdapter<Task> adapter;
  
    
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.todo_list);
     
        
        ListView listCourses = 
        		(ListView)findViewById(R.id.lstTodoItems);
        List<Task> tasks = new ArrayList<Task>();
        adapter = new TodoAdapter(this, tasks);
        listCourses.setAdapter(adapter);
        registerForContextMenu(listCourses);
        
    }
    
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		getMenuInflater().inflate(R.menu.context_menu, menu);
		
		AdapterView.AdapterContextMenuInfo info =
	            (AdapterView.AdapterContextMenuInfo) menuInfo;
	    
		LinearLayout tsk=(LinearLayout)info.targetView;
		
		String selectedTask = ((TextView) tsk.getChildAt(0)).getText().toString();

	    menu.setHeaderTitle(selectedTask);
	    if (selectedTask.startsWith("Call "))
	    {
	    	menu.add(0, R.id.menuItemCall, 1, selectedTask);
	    }

	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		AdapterContextMenuInfo info = (AdapterContextMenuInfo)
				item.getMenuInfo();
		int selectedItemIndex = info.position;
		switch (item.getItemId()){
		case R.id.menuItemDelete:
			adapter.remove(adapter.getItem(selectedItemIndex));
			break;
		case R.id.menuItemCall:
			String selectedTask =(String) item.getTitle() ;
			String callCommand="tel:"+selectedTask.substring(5);
			Intent dial = new Intent(Intent.ACTION_DIAL, 
					Uri.parse(callCommand)); 
					startActivity(dial); 
			//adapter.remove(adapter.getItem(selectedItemIndex));
			break;
			
		}
		return true;
	}
	
	
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    	getMenuInflater().inflate(R.menu.main, menu);
    	return true;
    }
    
    
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    	if (requestCode == 1337 && resultCode == RESULT_OK) {
    		String taskName = data.getStringExtra("title");
    		Date taskDate=(Date) data.getSerializableExtra("dueDate");
    		adapter.add(new Task(taskName, taskDate));
    	}
    }
    
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    	EditText taskNew = (EditText)findViewById(R.id.edtNewItem);
    	ListView lv=(ListView)findViewById(R.id.lstTodoItems);
    	int pos=lv.getSelectedItemPosition();
    	switch (item.getItemId()) {
    	case R.id.menuItemAdd:
    		
    		Intent intent = new Intent(this, AddNewTodoItemActivity.class);
    		startActivityForResult(intent, 1337);
    		break;
    	}
    	return true;
    }
    
}

    

