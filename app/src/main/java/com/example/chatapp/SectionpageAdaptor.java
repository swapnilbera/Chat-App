package com.example.chatapp;

import android.widget.Switch;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import org.jetbrains.annotations.NotNull;

public class SectionpageAdaptor extends FragmentPagerAdapter {
    public SectionpageAdaptor(@NonNull @NotNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @NotNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                Request_frag request_frag=new Request_frag();
                return request_frag;
            case 1:
                Chats_frag chats_frag=new Chats_frag();
                return chats_frag;
            case 2:
                Friends_frag friends_frag=new Friends_frag();
                return friends_frag;
            default:
                return null;
        }

    }

    @Override
    public int getCount() {
        return 3;
    }
    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
               return "Requests";
            case 1:
                return "Chats";
            case 2:
              return "Friends";
            default:
                return null;
        }
    }


}
