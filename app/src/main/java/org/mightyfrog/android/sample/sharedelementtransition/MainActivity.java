package org.mightyfrog.android.sample.sharedelementtransition;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * https://medium.com/android-news/easy-android-shared-element-transition-ac36952a4a4
 *
 * @author Shigehiro Soejima
 */
public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView rv = (RecyclerView) findViewById(R.id.recycler_view);
        rv.setAdapter(new MyAdapter());
        rv.setLayoutManager(new GridLayoutManager(this, 2));
    }

    /**
     *
     */
    private class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
        private final int[] COLORS = new int[]{
                0xfff44336, 0xffe91e63, 0xff9c27b0, 0xff673ab7, 0xff3f51b5, 0xff2196f3, 0xff03a9f4,
                0xff00bcd4, 0xff009688, 0xff4caf50, 0xff8bc34a, 0xffcddc39, 0xffffeb3b, 0xffffc107,
                0xffff9800, 0xffff5722, 0xff795548, 0xff9e9e9e, 0xff607d8b, 0xff212121
        };

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new MyViewHolder(LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.rv_item, parent, false));
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
            final MyViewHolder vh = (MyViewHolder) holder;
            vh.mTextView.setBackgroundColor(COLORS[position]);
            vh.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                    intent.putExtra("backgroundColor", COLORS[position]);

                    // Define the view that the animation will start from
                    ActivityOptionsCompat options =
                            ActivityOptionsCompat.makeSceneTransitionAnimation(MainActivity.this,
                                    vh.mTextView, getString(R.string.transition_string));
                    ActivityCompat.startActivity(MainActivity.this, intent, options.toBundle());
                }
            });
            vh.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    Intent intent = new Intent(MainActivity.this, ThirdActivity.class);
                    intent.putExtra("backgroundColor", COLORS[position]);

                    // Define the view that the animation will start from
                    ActivityOptionsCompat options =
                            ActivityOptionsCompat.makeSceneTransitionAnimation(MainActivity.this,
                                    vh.mTextView, getString(R.string.transition_string));
                    ActivityCompat.startActivity(MainActivity.this, intent, options.toBundle());

                    return false;
                }
            });
        }

        @Override
        public int getItemCount() {
            return COLORS.length;
        }

        /**
         *
         */
        class MyViewHolder extends RecyclerView.ViewHolder {
            TextView mTextView;

            public MyViewHolder(View itemView) {
                super(itemView);

                mTextView = (TextView) itemView.findViewById(R.id.text);
            }
        }
    }
}
