package co.com.mintic.ejeCafetero

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import org.json.JSONArray
import org.json.JSONException
import java.io.IOException
import java.util.ArrayList

class ListActivity : AppCompatActivity() {

    private lateinit var mWorkshops: ArrayList<Workshops>
    private lateinit var mAdapter: WorkshopAdapter
    private lateinit var recycler: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        recycler = findViewById(R.id.recyclerview)
        setupRecyclerView()
        initDataFromFile()

    }

    /**
     * Generates mock contact data to populate the UI from a JSON file in the
     * assets directory, called from the options menu.
     */
    private fun initDataFromFile() {
        val contactsString = readContactJsonFile()
        try {
            val contactsJson = JSONArray(contactsString)
            for (i in 0 until contactsJson.length()) {
                val contactJson = contactsJson.getJSONObject(i)
                val workshops = Workshops(
                    contactJson.getString("title"),
                    contactJson.getString("desc"),
                    contactJson.getString("imageUrl"),
                    contactJson.getString("rating")
                )
                Log.d(TAG, "generateContacts: $workshops")
                mWorkshops.add(workshops)
            }

            mAdapter.notifyDataSetChanged()
        } catch (e: JSONException) {
            e.printStackTrace()
        }
    }


    /**
     * Reads a file from the assets directory and returns it as a string.
     *
     * @return The resulting string.
     */
    private fun readContactJsonFile(): String? {
        var workshopString: String? = null
        try {
            val inputStream = assets.open("mock_workshops.json")
            val size = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()

            workshopString = String(buffer)
        } catch (e: IOException) {
            e.printStackTrace()
        }

        return workshopString
    }

    /**
     * Sets up the RecyclerView: empty data set, item dividers, swipe to delete.
     */
    private fun setupRecyclerView() {
        mWorkshops = arrayListOf()
        recycler.addItemDecoration(
            DividerItemDecoration(
                this,
                DividerItemDecoration.VERTICAL
            )
        )
        mAdapter = WorkshopAdapter(mWorkshops, this) { contact ->
            workshopOnClick(contact)
        }

        recycler.adapter = mAdapter
    }

    /* RecyclerView item is clicked. */
    private fun workshopOnClick(workshop: Workshops?) {
        Log.d(TAG, "Click on: $workshop")

    }


    companion object {
        private val TAG = ListActivity::class.java.simpleName
        const val KEY_NAME = "contact_extra_name"
        const val KEY_LAST_NAME = "contact_extra_last_name"
        const val KEY_CONTACT = "contact_extra"
    }
}