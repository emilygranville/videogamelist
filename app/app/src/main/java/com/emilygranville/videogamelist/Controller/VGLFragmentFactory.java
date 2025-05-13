package com.emilygranville.videogamelist.Controller;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentFactory;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class VGLFragmentFactory extends FragmentFactory {

    private static final String PACKAGE_NAME = "com.emilygranville.videogamelist.view";
    private MainActivity controller;

    public VGLFragmentFactory(MainActivity controller) {
        super();
        this.controller = controller;
    }

    public Fragment instantiate(@NonNull ClassLoader classLoader, @NonNull String className) {
        Class<? extends Fragment> fragmentClass = loadFragmentClass(classLoader, className);

        if (fragmentClass.getPackage().getName().equals(PACKAGE_NAME)) {
            try {
                Constructor<?>[] constructors = fragmentClass.getConstructors();
                assert constructors.length > 0 : "Fragment class does not have a constructor";
                return (Fragment) constructors[0].newInstance(controller);
            } catch (InvocationTargetException | IllegalAccessException | InstantiationException e) {
                final String emsg = String.format("Can't instantiate %s: ensure it's concrete and " +
                        "has a public constructor with a ControllerActivity-compatible parameter", fragmentClass);
                Log.e(MainActivity.VGL, emsg);
                e.printStackTrace();
            }
        }
        return super.instantiate(classLoader, className);
    }
}
