package adapter;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import you.with.com.signature.R;

public class PacketAdapter extends RecyclerView.Adapter<PacketAdapter.MyViewHolder> {
    private final Context context;
    private final List<ApplicationInfo> list;

    public PacketAdapter(Context context, List<ApplicationInfo> list) {
        this.context = context;
        this.list = list;
    }

    public interface OnItemClickLitener {
        void onItemClick(View view, int position);
    }

    private OnItemClickLitener mOnItemClickLitener;

    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener) {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.package_list_item, viewGroup, false);
        return new MyViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(final PacketAdapter.MyViewHolder myViewHolder, final int position) {
        ApplicationInfo infoBean = list.get(position);
        myViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int pos = myViewHolder.getLayoutPosition();
                mOnItemClickLitener.onItemClick(myViewHolder.itemView, pos);
            }
        });
        myViewHolder.appIcon.setImageDrawable(infoBean.loadIcon(context.getPackageManager()));
        PackageManager packageManager = context.getPackageManager();
        myViewHolder.appPackageName.setText(infoBean.packageName);
        myViewHolder.appName.setText(infoBean.loadLabel(packageManager).toString());
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView appPackageName, appName;
        ImageView appIcon;

        public MyViewHolder(View itemView) {
            super(itemView);
            appIcon = itemView.findViewById(R.id.appIcon);
            appPackageName = itemView.findViewById(R.id.appPackageName);
            appName = itemView.findViewById(R.id.appName);
        }
    }


}
