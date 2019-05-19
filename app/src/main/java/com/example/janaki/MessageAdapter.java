package com.example.janaki;

import android.app.Activity;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;


import java.util.List;
public class MessageAdapter extends ArrayAdapter<ChatBubble> {
    private Activity activity;
    private List<ChatBubble> messages;

    public MessageAdapter(Activity context, int resource, List<ChatBubble> objects) {
        super(context, resource, objects);
        this.activity = context;
        this.messages = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

        int layoutResource = 0; // determined by view type
        ChatBubble ChatBubble = getItem(position);
        int viewType = getItemViewType(position);

        if(ChatBubble.myMessage()=="centre"&& ChatBubble.getContent()!=null)
        {
            layoutResource=R.layout.activity_centre_bubble;
        }
        if (ChatBubble.myMessage()=="imageright" && ChatBubble.getContent() == null) {
            layoutResource = R.layout.activity_image_layout;

        }
        if (ChatBubble.myMessage()=="imageleft" && ChatBubble.getContent() == null) {
            layoutResource = R.layout.activity_image_left_layout;

        }
        if (ChatBubble.myMessage()=="right"&& ChatBubble.getContent() != null) {
            layoutResource = R.layout.activity_right_bubble;

        }
        if (ChatBubble.myMessage()=="left" && ChatBubble.getContent() != null) {
            layoutResource = R.layout.activity_left_bubble;

        }


        if (convertView != null) {
            holder = (ViewHolder) convertView.getTag();
        } else {
            convertView = inflater.inflate(layoutResource, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }

        //set message content
        if (ChatBubble.getContent() != null) {
            holder.msg.setText(ChatBubble.getContent());
        } else {
            Glide.with(activity).load(ChatBubble.getImage()).into(holder.imageView);
        }
        return convertView;
    }

    @Override
    public int getViewTypeCount() {
        // return the total number of view types. this value should never change
        // at runtime. Value 2 is returned because of left and right views.
        return 50;
    }

    @Override
    public int getItemViewType(int position) {
        // return a value between 0 and (getViewTypeCount - 1)
        return position%50;
    }

    public class ViewHolder {
        private TextView msg;
        private ImageView imageView;
        public ViewHolder(View v)
        {
            msg = (TextView) v.findViewById(R.id.txt_msg);
            imageView = (ImageView)v.findViewById(R.id.imageView_message_image);

        }
    }
}

