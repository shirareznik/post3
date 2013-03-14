package il.ac.huji.todolist;

import java.util.List;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class TodoAdapter extends ArrayAdapter<String> {
	public TodoAdapter(
			TodoListManagerActivity activity, List<String> tasks) {
		super(activity, android.R.layout.simple_list_item_1, tasks);
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		String task = getItem(position);
		LayoutInflater inflater = LayoutInflater.from(getContext());
		View view = inflater.inflate(R.layout.row, null);
		TextView tasktxt = (TextView)view.findViewById(R.id.taskTxt);
		tasktxt.setText(task);
		if (position % 2 == 1) {
		    tasktxt.setTextColor(Color.BLUE);
			//view.setBackgroundColor(Color.BLUE);  
		} else {
			tasktxt.setTextColor(Color.RED);
		    //view.setBackgroundColor(Color.RED);  
		}

		return view;
	}
}
