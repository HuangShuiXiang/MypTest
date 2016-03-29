package aidl.com.mvptest.card.cardModelView;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import com.materlistview.cardModelView.CardItemView;

import aidl.com.mvptest.R;
import aidl.com.mvptest.card.cardModel.ItemCard;

/**
 * Created by Administrator on 2016/3/28.
 */
public class ItemCardView extends CardItemView<ItemCard>{
    public ItemCardView(Context context) {
        super(context);
    }

    public ItemCardView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ItemCardView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void build(final ItemCard card) {
        super.build(card);
        TextView tv_name = (TextView)findViewById(R.id.tv_name);
        tv_name.setText(card.getName());
        tv_name.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                card.getOnAddFreshUIPressedListener().onButtonPressedListener(null,card);
            }
        });
    }
}
