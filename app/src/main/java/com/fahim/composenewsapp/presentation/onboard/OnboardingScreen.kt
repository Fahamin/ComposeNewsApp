package com.fahim.composenewsapp.presentation.onboard

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.fahim.composenewsapp.presentation.comon.NewsTaskButton
import com.fahim.composenewsapp.presentation.comon.NextButton
import com.fahim.composenewsapp.presentation.comon.PageIndicator
import com.fahim.composenewsapp.presentation.onboard.componets.Dimens.MediumPadding
import com.fahim.composenewsapp.presentation.onboard.componets.Dimens.MediumPadding2
import com.fahim.composenewsapp.presentation.onboard.componets.OnBoardPage
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnBoardingScreen() {
    Column(modifier = Modifier.fillMaxSize()) {
        val pagerState = rememberPagerState(initialPage = 0) {
            pages.size
        }

        val buttonState = remember {
            derivedStateOf {
                when (
                    pagerState.currentPage
                ) {
                    0 -> listOf("", "Next")
                    1 -> listOf("Back", "Next")
                    2 -> listOf("Back", "Get Started")
                    else -> listOf("", "")
                }
            }
        }
        HorizontalPager(state = pagerState) { index ->
            OnBoardPage(page = pages[index])
        }

        Spacer(modifier = Modifier.weight(1f))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = MediumPadding2)
                .navigationBarsPadding(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            PageIndicator(
                pageSize = pages.size,
                selectedPage = pagerState.currentPage
            )


            Row {

                val scope = rememberCoroutineScope()
                if (buttonState.value[0].isNotEmpty()) {
                    NewsTaskButton(
                        text = buttonState.value[0],
                        onclick = {
                            scope.launch {
                                pagerState.animateScrollToPage(page = pagerState.currentPage - 1)

                            }
                        },
                    )


                }
                NextButton(
                    text = buttonState.value[1],
                    onclick = {
                        scope.launch {
                            if (pagerState.currentPage == 3) {

                            } else {
                                pagerState.animateScrollToPage(
                                    page = pagerState.currentPage + 1
                                )

                            }

                        }
                    },
                )
            }
        }
        Spacer(modifier = Modifier.weight(0.5f))
    }
}