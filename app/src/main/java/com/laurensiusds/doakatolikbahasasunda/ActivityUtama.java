package com.laurensiusds.doakatolikbahasasunda;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

public class ActivityUtama extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private Fragment fragment = null;
    protected AdView adView;
    protected ProgressDialog pDialog;
    protected Dialog dialBox;
    public static String nav_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_utama);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        dialBox = createDialogBox();
        jalankanFragment(R.id.nav_syahadat);

        adView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);
    }

    private Dialog createDialogBox(){
        dialBox = new AlertDialog.Builder(this)
                .setTitle("Kaluar ti Aplikasi")
                .setMessage("Leres Anjeun teh bade kaluar ti ieu aplikasi?")
                .setPositiveButton("Sumuhun", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        Intent i = new Intent(ActivityUtama.this, ActivityInterstitial.class);
                        startActivity(i);
                        finish();
                    }
                })
                .setNegativeButton("Henteu", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        dialBox.dismiss();
                    }
                })
                .create();
        return dialBox;
    }

    @Override
    public void onPause() {
        if (adView != null) {
            adView.pause();
        }
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (adView != null) {
            adView.resume();
        }
    }

    @Override
    public void onDestroy() {
        if (adView != null) {
            adView.destroy();
        }
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            dialBox.show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_utama, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_keluar) {
            dialBox.show();
        }
        return true;
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        jalankanFragment(item.getItemId());
        return true;
    }

    public void jalankanFragment(int id){
        fragment = null;
        if (id == R.id.nav_tanda_salib) {
            nav_id = "nav_tanda_salib";
            fragment = new FragmentDoa();
        } else if (id == R.id.nav_syahadat) {
            nav_id = "nav_syahadat";
            fragment = new FragmentDoa();
        } else if (id == R.id.nav_perintah_allah) {
            nav_id = "nav_perintah_allah";
            fragment = new FragmentDoa();
        } else if (id == R.id.nav_perintah_gereja) {
            nav_id = "nav_perintah_gereja";
            fragment = new FragmentDoa();
        }else if (id == R.id.nav_bapa_kami) {
            nav_id = "nav_bapa_kami";
            fragment = new FragmentDoa();
        } else if (id == R.id.nav_salam_maria) {
            nav_id = "nav_salam_maria";
            fragment = new FragmentDoa();
        } else if (id == R.id.nav_kemuliaan) {
            nav_id = "nav_kemuliaan";
            fragment = new FragmentDoa();
        } else if (id == R.id.nav_doa_tobat) {
            nav_id = "nav_doa_tobat";
            fragment = new FragmentDoa();
        } else if (id == R.id.nav_doa_pagi) {
            nav_id = "nav_doa_pagi";
            fragment = new FragmentDoa();
        } else if (id == R.id.nav_doa_malam) {
            nav_id = "nav_doa_malam";
            fragment = new FragmentDoa();
        } else if (id == R.id.nav_rosario) {
            nav_id = "nav_rosario";
            fragment = new FragmentDoa();
        } else if (id == R.id.nav_peristiwa_gembira) {
            nav_id = "nav_peristiwa_gembira";
            fragment = new FragmentDoa();
        } else if (id == R.id.nav_peristiwa_mulia) {
            nav_id = "nav_peristiwa_mulia";
            fragment = new FragmentDoa();
        } else if (id == R.id.nav_peristiwa_sedih) {
            nav_id = "nav_peristiwa_sedih";
            fragment = new FragmentDoa();
        }else if (id == R.id.nav_keluar) {
            dialBox.show();
        }

        if (fragment != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.flUtama, fragment);
            ft.commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
    }
}
