import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.student_database.StudentData
import com.example.student_database.StudentRepositry
import kotlinx.coroutines.launch


class WordViewModel(private val repository: StudentRepositry) : ViewModel() {

    // Using LiveData and caching what allWords returns has several benefits:
    // - We can put an observer on the data (instead of polling for changes) and only update the
    //   the UI when the data actually changes.
    // - Repository is completely separated from the UI through the ViewModel.
    val allWords: LiveData<List<StudentData>> = repository.allDetails

    //ToDo  asLiveData removed
    /**
     * Launching a new coroutine to insert the data in a non-blocking way
     */
    fun insert(word: StudentData) = viewModelScope.launch {
        repository.insert(word)
    }
}

class StudentViewModel(private val repository: StudentRepositry) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(StudentViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return StudentViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}