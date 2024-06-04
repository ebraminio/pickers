package ir.rezarasuolzadeh.pickers

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.viewmodel.compose.viewModel
import com.eniac.sorena.ui.util.picker.Picker
import com.eniac.sorena.ui.util.picker.rememberPickerState
import ir.rezarasuolzadeh.pickers.ui.theme.DarkBlue
import ir.rezarasuolzadeh.pickers.ui.theme.LightBlue
import ir.rezarasuolzadeh.pickers.ui.theme.White
import ir.rezarasuolzadeh.pickers.ui.viewmodel.time.TimeViewModel

@Composable
fun TimePickerDialogCompose(
    showSeconds: Boolean = true,
    onTimeSelect: (String) -> Unit,
    onCancel: () -> Unit,
    timeViewModel: TimeViewModel = viewModel()
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(shape = RoundedCornerShape(15.dp))
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        LightBlue,
                        DarkBlue
                    )
                )
            )
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
            color = White,
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
                color = White,
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
            if(showSeconds) {
                Text(
                    modifier = Modifier
                        .padding(top = 20.dp),
                    text = ":",
                    color = White,
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
        }
        Box(
            modifier = Modifier
                .padding(top = 25.dp)
                .height(height = 0.3.dp)
                .fillMaxWidth()
                .background(color = White)
        )
        ConstraintLayout(
            modifier = Modifier
                .height(height = 50.dp)
                .fillMaxWidth()
        ) {
            val (confirm, cancel, divider) = createRefs()
            Text(
                modifier = Modifier
                    .width(width = 60.dp)
                    .height(height = 50.dp)
                    .padding(top = 5.dp)
                    .constrainAs(confirm) {
                        start.linkTo(parent.start)
                        end.linkTo(divider.start)
                        bottom.linkTo(parent.bottom)
                        top.linkTo(parent.top)
                    }
                    .noRippleClickable {
                        onCancel()
                    },
                text = "انصراف",
                color = White,
                fontFamily = FontFamily(Font(R.font.vazir_num)),
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
            Box(
                modifier = Modifier
                    .padding(vertical = 10.dp)
                    .height(height = 50.dp)
                    .width(width = 0.3.dp)
                    .background(color = White)
                    .constrainAs(divider) {
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        bottom.linkTo(parent.bottom)
                        top.linkTo(parent.top)
                    }
            )
            Text(
                modifier = Modifier
                    .width(width = 60.dp)
                    .height(height = 50.dp)
                    .padding(top = 5.dp)
                    .constrainAs(cancel) {
                        end.linkTo(parent.end)
                        start.linkTo(divider.end)
                        bottom.linkTo(parent.bottom)
                        top.linkTo(parent.top)
                    }
                    .noRippleClickable {
                        if(showSeconds) {
                            onTimeSelect("${hourPickerState.selectedItem}:${minutePickerState.selectedItem}:${secondPickerState.selectedItem}")
                        } else {
                            onTimeSelect("${hourPickerState.selectedItem}:${minutePickerState.selectedItem}")
                        }
                    },
                text = "ثبت",
                color = White,
                fontFamily = FontFamily(Font(R.font.vazir_num)),
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Preview
@Composable
fun TimePickerDialogPreview() {
    TimePickerDialogCompose(
        onTimeSelect = {},
        onCancel = {}
    )
}