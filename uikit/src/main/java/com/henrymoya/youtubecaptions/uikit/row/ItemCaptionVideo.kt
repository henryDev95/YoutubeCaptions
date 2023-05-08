package com.henrymoya.youtubecaptions.uikit.row

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.henrymoya.youtubecaptions.core.strings.clearEnters
import com.henrymoya.youtubecaptions.core.strings.toSecondMinute
import com.henrymoya.youtubecaptions.uikit.text.FTextBold
import com.henrymoya.youtubecaptions.uikit.text.FTextRegular

@Composable
fun ItemCaptionVideo(title: String, time: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 6.dp)
    ) {
        ConstraintLayout(
            Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            val (textLeft, textRigth) = createRefs()
            FTextBold(
                modifier = Modifier.width(60.dp)
                    .constrainAs(textLeft) {
                        start.linkTo(parent.start)
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                    },
                text = time.toSecondMinute(),
                fontSize = 16.sp
            )

            FTextRegular(
                modifier = Modifier
                    .constrainAs(textRigth) {
                        end.linkTo(parent.end)
                        start.linkTo(textLeft.end)
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                        width = Dimension.fillToConstraints
                    },
                text = title.clearEnters(),
                fontSize = 16.sp
            )

        }
    }
}

