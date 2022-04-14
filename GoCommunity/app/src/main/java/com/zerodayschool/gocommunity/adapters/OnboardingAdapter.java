package com.zerodayschool.gocommunity.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.zerodayschool.gocommunity.R;

import java.util.List;

public class OnboardingAdapter extends RecyclerView.Adapter<com.zerodayschool.gocommunity.adapters.OnboardingAdapter.OnboardingViewHolder>{

    private List<OnboardingItem> onboardingItems;
    public OnboardingAdapter(List<OnboardingItem> onboardingItems){
        this.onboardingItems = onboardingItems;
    }

    @NonNull
    @Override
    public OnboardingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new OnboardingViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.item_container_onboarding, parent, false
                )
        );
    }

    @Override
    public void onBindViewHolder(@NonNull OnboardingViewHolder holder, int position) {
    holder.setOnboardingData(onboardingItems.get(position));
    }

    @Override
    public int getItemCount() {
        return onboardingItems.size();
    }

    class OnboardingViewHolder extends RecyclerView.ViewHolder{
        private TextView textTitulo;
        private TextView textDescripsion;
        private ImageView imageOnbarding;

         OnboardingViewHolder(@NonNull View itemView) {
            super(itemView);
            textTitulo = itemView.findViewById(R.id.txtTitulo);
            textDescripsion=itemView.findViewById(R.id.txtDescripsion);
            imageOnbarding = itemView.findViewById(R.id.imageOnboarding);
        }

        void setOnboardingData(OnboardingItem onboardingItem){
             textTitulo.setText(onboardingItem.getTitulo());
             textDescripsion.setText(onboardingItem.getDescripsion());
             imageOnbarding.setImageResource(onboardingItem.getImage());
        }
    }
}
