package com.example.a84064.eatapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.PoiInfo;
import com.baidu.mapapi.utils.DistanceUtil;
import com.example.a84064.eatapp.Model.UrlText;
import com.example.a84064.eatapp.R;

import java.util.List;

/**
 * Created by imahe001 on 2015/9/29.
 */
public class PoiSearchAdapter extends BaseAdapter {

    private Context context;
    private List<PoiInfo> poiInfos;
    private final LatLng locationLatLng;
    UrlText u=new UrlText();

    public PoiSearchAdapter(Context context, List<PoiInfo> poiInfos, LatLng locationLatLng) {
        this.context = context;
        this.poiInfos = poiInfos;
        this.locationLatLng = locationLatLng;
    }

    @Override
    public int getCount() {
        return poiInfos.size();
    }

    @Override
    public Object getItem(int position) {
        return poiInfos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public String getStreet(int position) {
        return poiInfos.get(position).name;
    }

    public String getPoint(int position) {
        LatLng point=poiInfos.get(position).location;
        return u.LatlngToString(point);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.poisearch_item, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        PoiInfo poiInfo = poiInfos.get(position);
        holder.poisearch_name.setText(poiInfo.name);
        holder.poisearch_address.setText(poiInfo.address);
        String distance;
        if((double)DistanceUtil.getDistance(locationLatLng, poiInfo.location)<1000)
            distance=(int)DistanceUtil.getDistance(locationLatLng, poiInfo.location)+"m";
        else
            distance=(double)(Math.round(DistanceUtil.getDistance(locationLatLng, poiInfo.location)/1000)/10.0)+"km";
        holder.poisearch_distance.setText(distance);
        return convertView;
    }

    class ViewHolder {
        TextView poisearch_name;
        TextView poisearch_address;
        TextView poisearch_distance;

        public ViewHolder(View view) {
            poisearch_name = (TextView) view.findViewById(R.id.poisearch_name);
            poisearch_address = (TextView) view.findViewById(R.id.poisearch_address);
            poisearch_distance = (TextView) view.findViewById(R.id.poisearch_distance);
        }
    }
}
