package il.ac.huji.todolist;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

public class TodoListManagerActivity extends Activity {

    private ArrayAdapter<String> adapter;
    private List<String> tasks;
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.todo_list);
        
        tasks = new ArrayList<String>();
      
        ListView listCourses = 
        		(ListView)findViewById(R.id.lstTodoItems);
        adapter = new CourseDisplayAdapter(this, tasks);
        listCourses.setAdapter(adapter);
        
        /*
        findViewById(R.id.buttonAdd).setOnClickListener(
        		new OnClickListener() {

					@Override
					public void onClick(View v) {
						adapter.add(new Course(
								"iPhone Application Development", 3));
						//adapter.getItem(12);
					}
        			
        		});
        */
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    	getMenuInflater().inflate(R.menu.main, menu);
    	return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    	EditText taskNew = (EditText)findViewById(R.id.edtNewItem);
    	ListView lv=(ListView)findViewById(R.id.lstTodoItems);
    	int pos=lv.getSelectedItemPosition();
    	//ListView.getSelectedItem()
    	switch (item.getItemId()) {
    	case R.id.menuItemAdd:
    		adapter.add(taskNew.getText().toString());
    		break;
    	case R.id.menuItemDelete:
    		tasks.remove(pos);
    		adapter.notifyDataSetChanged();
    		break;
    	}
    	return true;
    }
}

    

