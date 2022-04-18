package hu.bme.aut.f1calendar.UI.Main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import hu.bme.aut.f1calendar.Model.Comment
import hu.bme.aut.f1calendar.R
import androidx.activity.viewModels
import androidx.navigation.fragment.NavHostFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity: AppCompatActivity() {
    private val viewModel: RaceViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //TODO: REMOVE
        /*
        val button = findViewById<Button>(R.id.button)
        val button1 = findViewById<Button>(R.id.button2)
        val button2 = findViewById<Button>(R.id.button3)
        val comment = Comment(0, "test", "3022 Hungarian Grand Prix");

        button.setOnClickListener {
            viewModel.getRaceRepositoryLiveData().insertComment(comment)
        }

        button1.setOnClickListener {
            viewModel.getRaceRepositoryLiveData().loadComments(comment.raceID)
        }

        button2.setOnClickListener {
            val commentD = viewModel.getRaceRepositoryLiveData().loadComments(comment.raceID)[0]
            viewModel.getRaceRepositoryLiveData().deleteComment(commentD)

        } */



    }
}