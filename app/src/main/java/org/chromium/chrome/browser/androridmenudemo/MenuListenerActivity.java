package org.chromium.chrome.browser.androridmenudemo;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MenuListenerActivity extends AppCompatActivity {

    private static final String TAG = "MenuDemo";
    private TextView tvMenuRegister;


    private String[] arrays =
            {
                    " 空旷冷落的古旧行宫，", "只有宫花寂寞地艳红。", "几个满头白发的宫女，", "闲坐无事谈论唐玄宗。",
                    "幽静的山谷里看不见人，", "只能听到那说话的声音。", "落日的影晕映入了深林，", "又照在青苔上景色宜人。",
                    "独坐幽篁里，", "弹琴复长啸。", "深林人不知，", "明月来相照。",
                    "孤云将野鹤，", "岂向人间住。", "莫买沃洲山，", "时人已知处。"
            };
    private List<String> stringList;
    private ArrayAdapter<String> adapter;
    private ArrayList<String> dataLists;
    private ListView lv;
    private RvAdapter rvAdapter;
    private RecyclerViewImplementsContextMenu rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_listener);
        tvMenuRegister = findViewById(R.id.tv_menu_register);

        registerForContextMenu(tvMenuRegister);
        tvMenuRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.showContextMenu();
            }
        });

        tvMenuRegister.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Toast.makeText(MenuListenerActivity.this, "我是被长按事件所触发的", Toast.LENGTH_SHORT).show();
                //返回false将会继续触发显示上下文菜单的逻辑，返回true将不会触发该逻辑了
                return false;
            }
        });

        TextView tvMenuListener = findViewById(R.id.tv_menu_listener);

        View.OnCreateContextMenuListener contextMenuListener = new View.OnCreateContextMenuListener() {
            @Override
            public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
                menu.setHeaderTitle("contextMenuListener");
                menu.setHeaderIcon(R.mipmap.ic_launcher_round);
                menu.add(0, Menu.FIRST + 1, 1, "contextMenuListener1");
                menu.add(0, Menu.FIRST + 2, 2, "contextMenuListener2");
                menu.add(0, Menu.FIRST + 3, 3, "contextMenuListener3");
                menu.add(0, Menu.FIRST + 4, 4, "contextMenuListener4");
            }
        };
        tvMenuListener.setOnCreateContextMenuListener(contextMenuListener);

        final TextView tvChange = findViewById(R.id.tv_change);
        tvChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (lv.getVisibility() == View.VISIBLE) {
                    tvChange.setText("RecyclerView");
                    lv.setVisibility(View.GONE);
                    rv.setVisibility(View.VISIBLE);
                } else {
                    tvChange.setText("ListView");
                    lv.setVisibility(View.VISIBLE);
                    rv.setVisibility(View.GONE);
                }

            }
        });

        lv = findViewById(R.id.lv);
        rv = findViewById(R.id.rv);


        stringList = Arrays.asList(arrays);
        dataLists = new ArrayList<>();
        dataLists.addAll(stringList);

        //RecyclerView
        rv.setLayoutManager(new LinearLayoutManager(this));
//        rv.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL)); //Android原生提供的分割线
        rv.addItemDecoration(new DefineDividerItemDecoration(this, DefineDividerItemDecoration.VERTICAL_LIST));
        rvAdapter = new RvAdapter(this, dataLists);
        rv.setAdapter(rvAdapter);

        rv.setOnCreateContextMenuListener(new View.OnCreateContextMenuListener() {
            @Override
            public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
                menu.setHeaderTitle("recyclerView");
                menu.setHeaderIcon(R.mipmap.ic_launcher_round);
                menu.add(0, Menu.FIRST + 1, 1, "recyclerViewListener1");
                menu.add(0, Menu.FIRST + 2, 2, "recyclerViewListener2");
                menu.add(0, Menu.FIRST + 3, 3, "recyclerViewListener3");
                menu.add(0, Menu.FIRST + 4, 4, "recyclerViewListener4");
            }
        });


        //ListView
        this.adapter = new ArrayAdapter<>(this, android.R.layout.simple_expandable_list_item_1, dataLists);
        lv.setAdapter(this.adapter);
        lv.setOnCreateContextMenuListener(new View.OnCreateContextMenuListener() {
            @Override
            public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
                menu.setHeaderTitle("listView");
                menu.setHeaderIcon(R.mipmap.ic_launcher_round);
                menu.add(0, Menu.FIRST + 1, 1, "listViewListener1");
                menu.add(0, Menu.FIRST + 2, 2, "listViewListener2");
                menu.add(0, Menu.FIRST + 3, 3, "listViewListener3");
                menu.add(0, Menu.FIRST + 4, 4, "listViewListener4");
            }
        });
        //设置条目点击事件
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                view.showContextMenu();
            }
        });
        //设置条目的长按事件
        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MenuListenerActivity.this, dataLists.get(position), Toast.LENGTH_SHORT).show();
                //返回false将会继续触发显示上下文菜单的逻辑，返回true将不会触发该逻辑了
                return false;
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0, Menu.FIRST + 1, 1, "optionsMenu1");
        menu.add(0, Menu.FIRST + 2, 2, "optionsMenu2");
        menu.add(0, Menu.FIRST + 3, 3, "optionsMenu3");
        menu.add(0, Menu.FIRST + 4, 4, "optionsMenu4");
        return super.onCreateOptionsMenu(menu);
    }

    //重写onOptionsItemSelected方法
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //获取菜单条目的title值
        CharSequence title = item.getTitle();
        //通过条目的id值来区分所点击的菜单条目
        switch (item.getItemId()) {
            case Menu.FIRST + 1:
                break;
            case Menu.FIRST + 2:
                break;
            case Menu.FIRST + 3:
                break;
            case Menu.FIRST + 4:
                break;
        }
        Toast.makeText(this, title, Toast.LENGTH_SHORT).show();
        return super.onOptionsItemSelected(item);
    }

    //重写Activity的onCreateContextMenu方法
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        //给菜单设置头标题
        menu.setHeaderTitle("OnCreateContextMenu");
        //给菜单设置头按钮
        menu.setHeaderIcon(R.mipmap.ic_launcher_round);
        menu.add(0, Menu.FIRST + 1, 1, "contextMenu1");
        menu.add(0, Menu.FIRST + 2, 2, "contextMenu2");
        menu.add(0, Menu.FIRST + 3, 3, "contextMenu3");
        menu.add(0, Menu.FIRST + 4, 4, "contextMenu4");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        CharSequence title = item.getTitle();
        ContextMenu.ContextMenuInfo itemMenuInfo = item.getMenuInfo();
        AdapterView.AdapterContextMenuInfo adapterContextMenuInfo = (AdapterView.AdapterContextMenuInfo) itemMenuInfo;
        if (adapterContextMenuInfo != null) {
            Log.e(TAG, "===" + adapterContextMenuInfo.position);
            Log.e(TAG, "===" + arrays[adapterContextMenuInfo.position]);
            dataLists.remove(adapterContextMenuInfo.position);
            if ("listView".equals(title)) {
                adapter.notifyDataSetChanged();
            } else {
                rvAdapter.notifyDataSetChanged();
            }

        }


        switch (item.getItemId()) {
            case Menu.FIRST + 1:
//                stringList.remove(1);
                break;
            case Menu.FIRST + 2:
                break;
            case Menu.FIRST + 3:
                break;
            case Menu.FIRST + 4:
                break;
        }
        Toast.makeText(this, title + "===", Toast.LENGTH_SHORT).show();
        return super.onContextItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterForContextMenu(tvMenuRegister);
    }


}
