/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.datafrominternet;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import com.example.android.datafrominternet.utilities.NetworkUtils;

import java.io.IOException;
import java.net.URL;
import java.util.concurrent.Executor;

public class MainActivity extends AppCompatActivity {

    private EditText mSearchBoxEditText;

    private TextView mUrlDisplayTextView;

    private TextView mSearchResultsTextView;

    private Executor mExecutor;

    private Handler mHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSearchBoxEditText = (EditText) findViewById(R.id.et_search_box);

        mUrlDisplayTextView = (TextView) findViewById(R.id.tv_url_display);
        mSearchResultsTextView = (TextView) findViewById(R.id.tv_github_search_results_json);

        MyApplication app = (MyApplication) getApplication();
        mExecutor = (Executor) app.executorService;
        mHandler = app.mainThreadHandler;
    }

    /**
     * This method retrieves the search text from the EditText, constructs the URL
     * (using {@link NetworkUtils}) for the github repository you'd like to find, displays
     * that URL in a TextView, and finally fires off an AsyncTask to perform the GET request using
     * our (not yet created) {@link GithubQueryTask}
     */
    private void makeGithubSearchQuery() {
        String githubQuery = mSearchBoxEditText.getText().toString();
        URL githubSearchUrl = NetworkUtils.buildUrl(githubQuery);
        mUrlDisplayTextView.setText(githubSearchUrl.toString());
        // (4) Create a new GithubQueryTask and call its execute method, passing in the url to query
        mSearchResultsTextView.setText("Sending request to GitHub. Please be patient...");
        mExecutor.execute(() -> {
            try {
                String searchResult = NetworkUtils.getResponseFromHttpUrl(githubSearchUrl);
                mHandler.post(() -> {
                    mSearchResultsTextView.setText(searchResult);
                });
            } catch (IOException e) {
                mHandler.post(() -> {
                    mSearchResultsTextView.setText("Can not get HTTP response: \n" + e.toString());
                });
            }
        });
    }

    // (1) Create a class called GithubQueryTask that extends AsyncTask<URL, Void, String>
    // (2) Override the doInBackground method to perform the query. Return the results. (Hint: You've already written the code to perform the query)
    // (3) Override onPostExecute to display the results in the TextView

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemThatWasClickedId = item.getItemId();
        if (itemThatWasClickedId == R.id.action_search) {
            makeGithubSearchQuery();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
