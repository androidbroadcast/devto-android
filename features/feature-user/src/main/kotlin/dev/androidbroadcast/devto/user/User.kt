package dev.androidbroadcast.devto.user

import android.text.format.DateFormat
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.ScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation
import com.google.accompanist.flowlayout.FlowRow
import com.google.accompanist.insets.statusBarsPadding
import dev.androidbroadcast.devto.api.result.Result
import dev.androidbroadcast.devto.theme.Dimens
import dev.androidbroadcast.devto.theme.Typography
import dev.androidbroadcast.devto.theme.screenContentPaddings
import dev.androidbroadcast.devto.user.model.User

@Composable
fun CurrentUser() = CurrentUser(viewModel = hiltViewModel())

@Composable
internal fun CurrentUser(viewModel: UserViewModel) {
    val result: Result<User>? by viewModel.user.collectAsState()
    if (result == null) {
        ScreenProgress()
    } else if (result is Result.Success<*>) {
        val user = (result as Result.Success<User>).value
        CurrentUserContent(user)
    } else if (result is Result.Failure<*>) {
        val error = (result as Result.Failure<*>).error
        Text(
            text = error?.message ?: "Unknown error",
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Composable
private fun CurrentUserContent(user: User) {
    Card(
        Modifier
            .fillMaxWidth()
            .scrollable(ScrollState(0), Orientation.Vertical)
            .statusBarsPadding(),
    ) {
        Column(
            Modifier
                .fillMaxWidth()
                .screenContentPaddings()
        ) {
            Image(
                painter = rememberImagePainter(data = user.profileImageUrl,
                    builder = {
                        transformations(CircleCropTransformation())
                    }
                ),
                contentDescription = null,
                alignment = Alignment.Center,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .width(120.dp)
                    .height(120.dp)
            )
            Text(text = user.name, style = Typography.h5)
            Spacer(modifier = Modifier.height(Dimens.paddings.default))
            user.summary?.let { summary ->
                Text(text = summary)
                Spacer(modifier = Modifier.height(Dimens.paddings.default))
            }
            FlowRow(mainAxisSpacing = Dimens.paddings.default) {
                user.location?.let { location ->
                    Row {
                        Image(Icons.Default.LocationOn, contentDescription = null)
                        Text(text = location)
                    }
                }
                val dateFormat = DateFormat.getMediumDateFormat(LocalContext.current)
                Text(text = stringResource(id = R.string.joined_on, dateFormat.format(user.joinedAt)))
            }
        }
    }
}

@Composable
private fun ScreenProgress() {
    CircularProgressIndicator(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .wrapContentSize(Alignment.Center)
    )
}

