package bgroseclose.leagueofyou.Adapters

import android.content.Context
import android.support.v4.view.PagerAdapter
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView

class ChampionSkinAdapter(val context: Context, val images: Array<Int>): PagerAdapter() {

    override fun isViewFromObject(view: View, anyObject: Any): Boolean {
       return view == anyObject
    }

    override fun getCount(): Int {
        images.size
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        var image: ImageView = ImageView(context)
        image.scaleType = ImageView.ScaleType.CENTER_CROP
        image.setImageResource(images[position])
        container.addView(image, 0)
        return image
    }

    override fun destroyItem(container: ViewGroup, position: Int, imageView: Any) {
        container.removeView(imageView as ImageView?)
    }
}