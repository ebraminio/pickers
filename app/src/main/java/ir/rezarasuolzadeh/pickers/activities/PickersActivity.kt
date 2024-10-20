package ir.rezarasuolzadeh.pickers.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import ir.rezarasuolzadeh.pickers.ui.dialog.DateDialog
import ir.rezarasuolzadeh.pickers.ui.dialog.TimeDialog
import ir.rezarasuolzadeh.pickers.ui.theme.PickersTheme
import ir.rezarasuolzadeh.pickers.ui.theme.White
import ir.rezarasuolzadeh.pickers.utils.enums.MonthType
import ir.rezarasuolzadeh.pickers.utils.enums.TimeOutputType
import ir.rezarasuolzadeh.pickers.utils.enums.TimeType

class PickersActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PickersTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = White
                ) {
                    val isTimeDialogVisible = remember { mutableStateOf(value = false) }
                    val isDateDialogVisible = remember { mutableStateOf(value = true) }

                    if (isTimeDialogVisible.value) {
                        ShowTimeDialog {
                            isTimeDialogVisible.value = false
                        }
                    }

                    if (isDateDialogVisible.value) {
                        ShowDateDialog {
                            isDateDialogVisible.value = false
                        }
                    }
                }
            }
        }
    }

    /**
     * show the time picker dialog with it's parameters.
     */
    @Composable
    private fun ShowTimeDialog(onCancel: () -> Unit) {
        TimeDialog(
            initialHour = 2,
            initialMinute = 13,
            initialSecond = 7,
            initialTimeType = TimeType.PM,
            outputType = TimeOutputType.ENGLISH,
            is12Hour = true,
            showSeconds = true,
            onCancel = onCancel
        )
    }

    /**
     * show the date picker dialog with it's parameters.
     */
    @Composable
    private fun ShowDateDialog(onCancel: () -> Unit) {
        DateDialog(
            initialYear = 1401,
            initialMonth = MonthType.ABAN,
            initialDay = 23,
            yearRange = 1375..1403,
            onCancel = onCancel
        )
    }

}