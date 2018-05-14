package com.example.ishanpant.colorsqliteapp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private Context context;
    private Cursor cursor;
    private Cursor cur;
    private String color;
    private int id;
    private ColorPojo colorPojo = new ColorPojo();
    private List<ColorPojo> colorPojoList = new ArrayList<>();
    private SqliteDataHelper sqliteDataHelper;

    public RecyclerViewAdapter(Context context, Cursor cursor) {
        this.context = context;
        this.cursor = cursor;
    }

    public void setColour(Cursor c) {
        this.cur = c;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_row, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        if (!cursor.moveToPosition(position)) {
            return;
        }

        sqliteDataHelper = new SqliteDataHelper(context);

        final String name = cursor.getString(cursor.getColumnIndex(SqliteDataHelper.COLUMN_NAME));
       /* colorPojo.setName(name);
        colorPojoList.add(colorPojo);*/

        holder.textView.setText(name);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                View v = LayoutInflater.from(context).inflate(R.layout.image_view, null);
                final ImageView imageView = (ImageView) view.findViewById(R.id.imageView);
                builder.setView(v);
                builder.setNeutralButton("Here", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        sqliteDataHelper.addColor(Color.GREEN);
                        Toast.makeText(context, "Color added successfully...", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(context, MainActivity.class);
                        context.startActivity(intent);
                    }
                });
                builder.show();
                color = cursor.getString(cursor.getColumnIndex(SqliteDataHelper.COLUMN_COLOR));
                if (color != null) {
                   holder.cardView.setBackgroundColor(Integer.parseInt(color));
                }
            }
        });

        /*if (!cur.moveToPosition(position)) {
            return;
        }*/

        /*colorPojo.setColor(Integer.parseInt(color));
        colorPojoList.add(colorPojo);*/



        /*SqliteDataHelper sqliteDataHelper = new SqliteDataHelper(context);
        SQLiteDatabase db = sqliteDataHelper.getWritableDatabase();*/


        /*long num = cursor.getLong(cursor.getColumnIndex(SqliteDataHelper.COLUMN_ID));
        colorPojo.setColor(color);
        colorPojoList.add(colorPojo);*/


    }

    @Override
    public int getItemCount() {
        return cursor.getCount();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textView;
        private CardView cardView;

        public ViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.textView);
            cardView = (CardView) itemView.findViewById(R.id.cardView);
        }
    }
}
