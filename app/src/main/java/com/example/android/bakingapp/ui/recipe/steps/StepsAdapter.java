package com.example.android.bakingapp.ui.recipe.steps;

import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.bakingapp.R;
import com.example.android.bakingapp.data.Step;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.android.bakingapp.ui.utils.UiUtils.backgroundTarget;


class StepsAdapter extends RecyclerView.Adapter<StepsAdapter.StepsViewHolder> {

    private Step[] steps;
    private OnStepClicked onStepClickedListener;

    @Override
    public StepsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new StepsViewHolder(inflater.inflate(R.layout.steps_item, parent, false));

    }

    @Override
    public void onBindViewHolder(StepsViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return steps == null ? 0 : steps.length;
    }

    public void setSteps(Step[] steps) {
        this.steps = steps;
        notifyDataSetChanged();
    }

    void setOnStepClickedListener(OnStepClicked onStepClickedListener) {
        this.onStepClickedListener = onStepClickedListener;
    }


    class StepsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private final Context mContext;

        @BindView(R.id.tv_step)
        TextView stepTextView;

        StepsViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mContext = itemView.getContext();

            itemView.setOnClickListener(this);
        }

        void bind(final int itemIndex) {
            final Step step = steps[itemIndex];
            stepTextView.setText(step.getShortDescription());
            //textViewStep.setText(String.format(getContext().getString(R.string.step_number), position));
            if (!"".equals(step.getThumbnailURL())) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    Picasso.with(mContext).load(Uri.parse(step.getThumbnailURL())).into(backgroundTarget(mContext, stepTextView));
                }
            }

        }

        @Override
        public void onClick(View v) {
            final int clickedPosition = getAdapterPosition();
            onStepClickedListener.stepClicked(steps[clickedPosition]);
        }
    }
}
