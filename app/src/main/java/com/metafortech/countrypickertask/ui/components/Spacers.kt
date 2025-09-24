package com.metafortech.countrypickertask.ui.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import ir.kaaveh.sdpcompose.sdp


@Composable
fun HSpacer(width: Int) = Spacer(modifier = Modifier.width(width.sdp))

@Composable
fun VSpacer(height: Int) = Spacer(modifier = Modifier.height(height.sdp))
