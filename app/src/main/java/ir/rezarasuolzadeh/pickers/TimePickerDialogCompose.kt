package ir.rezarasuolzadeh.pickers

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.eniac.sorena.ui.util.picker.Picker
import com.eniac.sorena.ui.util.picker.rememberPickerState
import ir.rezarasuolzadeh.pickers.ui.theme.White

@Composable
fun TimePickerDialogCompose(
    onTimeSelect: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(shape = RoundedCornerShape(15.dp))
            .background(color = White)
    ) {
        val hours = remember { (0..23).map { if (it < 10) "0$it" else "$it" } }
        val minutes = remember { (0..59).map { if (it < 10) "0$it" else "$it" } }
        val seconds = remember { (0..59).map { if (it < 10) "0$it" else "$it" } }
        val hourPickerState = rememberPickerState()
        val minutePickerState = rememberPickerState()
        val secondPickerState = rememberPickerState()
        Text(
            modifier = Modifier
                .padding(top = 10.dp)
                .fillMaxWidth(),
            text = "انتخاب زمان",
            color = Black,
            fontFamily = FontFamily(Font(R.font.vazir_num)),
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Picker(
                state = hourPickerState,
                items = hours,
                visibleItemsCount = 3,
                modifier = Modifier
                    .padding(top = 25.dp)
                    .width(65.dp),
                textModifier = Modifier.padding(8.dp),
                textStyle = TextStyle(fontSize = 20.sp)
            )
            Text(
                modifier = Modifier
                    .padding(top = 20.dp),
                text = ":",
                color = Black,
                fontFamily = FontFamily(Font(R.font.vazir_num)),
                fontWeight = FontWeight.ExtraBold
            )
            Picker(
                state = minutePickerState,
                items = minutes,
                visibleItemsCount = 3,
                modifier = Modifier
                    .padding(top = 25.dp)
                    .width(65.dp),
                textModifier = Modifier.padding(8.dp),
                textStyle = TextStyle(fontSize = 20.sp)
            )
            Text(
                modifier = Modifier
                    .padding(top = 20.dp),
                text = ":",
                color = Black,
                fontFamily = FontFamily(Font(R.font.vazir_num)),
                fontWeight = FontWeight.ExtraBold
            )
            Picker(
                state = secondPickerState,
                items = seconds,
                visibleItemsCount = 3,
                modifier = Modifier
                    .padding(top = 25.dp)
                    .width(65.dp),
                textModifier = Modifier.padding(8.dp),
                textStyle = TextStyle(fontSize = 20.sp)
            )
        }
        Text(
            modifier = Modifier
                .padding(top = 25.dp)
                .fillMaxWidth()
                .noRippleClickable {
                    onTimeSelect("${hourPickerState.selectedItem}:${minutePickerState.selectedItem}:${secondPickerState.selectedItem}")
                },
            text = "ثبت",
            color = Black,
            fontFamily = FontFamily(Font(R.font.vazir_num)),
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(height = 10.dp))
    }
}

@Preview
@Composable
fun TimePickerDialogPreview() {
    TimePickerDialogCompose(
        onTimeSelect = {}
    )
}