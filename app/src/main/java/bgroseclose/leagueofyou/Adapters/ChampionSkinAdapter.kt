package bgroseclose.leagueofyou.Adapters

import android.app.Activity
import android.content.Context
import android.support.v4.view.PagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import bgroseclose.leagueofyou.R
import com.squareup.picasso.Picasso

class ChampionSkinAdapter(val activity: Activity, val images: ArrayList<String>, val picasso: Picasso): PagerAdapter() {

    override fun isViewFromObject(view: View, anyObject: Any): Boolean {
       return view == anyObject
    }

    override fun getCount(): Int {
        return images.size
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        var inflater: LayoutInflater = activity.applicationContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        var itemView: View = inflater.inflate(R.layout.champion_viewpager_item, container, false)

        var image: ImageView = itemView.findViewById(R.id.champion_skin_pager_item)
        picasso.load(images[position]).into(image)
        container.addView(itemView)
        return itemView
    }

    override fun destroyItem(container: ViewGroup, position: Int, imageView: Any) {
        container.removeView(imageView as ImageView?)
    }
}