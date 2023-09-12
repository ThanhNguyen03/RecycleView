package com.example.recycleview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Context;
import android.os.Bundle;

import com.example.recycleview.ImageAdapter;
import com.example.recycleview.R;
import com.example.recycleview.TextnImage;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    List<TextnImage> textnImageList = new ArrayList<>();
    List<String> listImageNames = new ArrayList<>();
    List<Integer> listImageIds = new ArrayList<>();
    private RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        parseXML();
        recyclerView = findViewById(R.id.recycleView);
        ImageAdapter myAdapter = new ImageAdapter(listImageNames,listImageIds);
        recyclerView.setAdapter(myAdapter);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 1)) ;
    }
    private void parseXML() {
        XmlPullParserFactory parserFactory;
        try {
            parserFactory = XmlPullParserFactory.newInstance();
            XmlPullParser parser = parserFactory.newPullParser();
            InputStream is = getAssets().open("data.xml");
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES,false);
            parser.setInput(is, null);

            processParsing(parser);


        } catch (XmlPullParserException e) {
        } catch (IOException e) {
        }
    }
    private void processParsing(XmlPullParser parser) throws IOException,XmlPullParserException{
        int eventType = parser.getEventType();
        TextnImage currentCountry = null;

        while (eventType != XmlPullParser.END_DOCUMENT) {
            String eltName = null;

            switch (eventType) {
                case XmlPullParser.START_TAG:
                    eltName = parser.getName();

                    if ("item".equals(eltName)) {
                        currentCountry = new TextnImage();
                        textnImageList.add(currentCountry);
                    } else if (currentCountry != null) {
                        if ("image".equals(eltName)) {
                            currentCountry.setImageID(parser.nextText());
                            listImageIds.add(getImageResourceId(this,currentCountry.getImageID()));
                        } else if ("text".equals(eltName)) {
                            currentCountry.setName(parser.nextText());
                            listImageNames.add(currentCountry.getName());
                        }
                    }
                    break;
            }
            eventType = parser.next();
        }
    }
    private static int getImageResourceId(Context context, String imageFileName) {
        // Chuyển đổi tên tệp hình ảnh thành id hình ảnh
        int imageResourceId = context.getResources().getIdentifier(
                imageFileName, "drawable", context.getPackageName());
        return imageResourceId;
    }
}
