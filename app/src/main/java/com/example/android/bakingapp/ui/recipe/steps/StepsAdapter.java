package com.example.android.bakingapp.ui.recipe.steps;

import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.android.bakingapp.R;
import com.example.android.bakingapp.data.Step;
import com.squareup.picasso.Picasso;

import static com.example.android.bakingapp.ui.utils.UiUtils.backgroundTarget;


public class StepsAdapter extends ArrayAdapter<Step> {
    private static final String TAG = StepsAdapter.class.getSimpleName();
    private OnStepClicked onStepClickedListener;

    public StepsAdapter(@NonNull Context context) {
        super(context, R.layout.steps_item);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        final Step step = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(this.getContext())
                    .inflate(R.layout.steps_item, parent, false);
        }

        TextView textViewStep = (TextView) convertView.findViewById(R.id.tv_step);
        textViewStep.setText(step.getShortDescription());
        //textViewStep.setText(String.format(getContext().getString(R.string.step_number), position));
        if (!"".equals(step.getThumbnailURL())){
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                Picasso.with(getContext()).load(Uri.parse(step.getThumbnailURL())).into(backgroundTarget(getContext(), textViewStep));
            }
        }

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onStepClickedListener != null) {
                    onStepClickedListener.stepClicked(step);
                } else {
                    Log.w(TAG, "no step click listener added!");
                }
            }
        });

        return convertView;
    }

    public void setOnStepClickedListener(OnStepClicked onStepClickedListener) {
        this.onStepClickedListener = onStepClickedListener;
    }
}
