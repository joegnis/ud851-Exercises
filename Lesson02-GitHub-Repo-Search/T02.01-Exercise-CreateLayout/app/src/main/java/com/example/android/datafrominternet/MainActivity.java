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
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    // (26) Create an EditText variable called mSearchBoxEditText
    EditText mSearchBoxEditText;

    // (27) Create a TextView variable called mUrlDisplayTextView
    TextView mUrlDisplayTextView;
    // (28) Create a TextView variable called mSearchResultsTextView
    TextView mSearchResultsTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // (29) Use findViewById to get a reference to mSearchBoxEditText
        mSearchBoxEditText = findViewById(R.id.et_search_box);

        // (30) Use findViewById to get a reference to mUrlDisplayTextView
        mUrlDisplayTextView = findViewById(R.id.tv_url_display);
        // (31) Use findViewById to get a reference to mSearchResultsTextView
        mSearchResultsTextView = findViewById(R.id.tv_github_search_results_json);
    }
}
