package signature;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.gyf.barlibrary.ImmersionBar;

import java.util.ArrayList;
import java.util.List;

import adapter.PacketAdapter;
import you.with.com.signature.R;

public class PackageListActivity extends Activity {

    PacketAdapter mAdapter;
    RecyclerView mRecyclerView;
    List<ApplicationInfo> mData = new ArrayList<>();

    private ImmersionBar mImmersionBar = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        mRecyclerView = findViewById(R.id.mRecycleView);
        mImmersionBar = ImmersionBar.with(this);
        mImmersionBar.fitsSystemWindows(true).keyboardEnable(true).statusBarDarkFont(true, 0.2f).barColor(R.color.theme_color).init();
        List<ApplicationInfo> apps = getPackageManager().getInstalledApplications(0);

        // 过滤系统应用
        for (ApplicationInfo info : apps) {
            if ((info.flags & info.FLAG_SYSTEM) == 0) {
                mData.add(info);
            }
        }
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new PacketAdapter(this, mData);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickLitener(new PacketAdapter.OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent data = new Intent();
                data.putExtra("packageName", mData.get(position).packageName);
                setResult(0, data);
                finish();
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mImmersionBar != null)
            mImmersionBar.destroy();
    }
//    @Override
//    protected void onListItemClick(ListView l, View v, int position, long id) {
//        Intent data = new Intent();
//        data.putExtra("packageName", mAdapter.getItem(position).packageName);
//        setResult(0, data);
//        finish();
//    }

}
