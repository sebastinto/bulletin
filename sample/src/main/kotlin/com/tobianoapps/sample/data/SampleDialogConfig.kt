package com.tobianoapps.sample.data

import androidx.compose.ui.unit.dp
import com.tobianoapps.bulletin.data.config.BulletinConfig
import com.tobianoapps.bulletin.data.config.style.BulletinCardStyle
import com.tobianoapps.bulletin.data.config.style.BulletinStyle

val dialogBulletinConfig = BulletinConfig.bulletinConfig {
    showLatestOnly = true
    style = BulletinStyle.bulletinStyle {
        cardStyle = BulletinCardStyle.bulletinCardStyle {
            headerDividerPaddingHorizontal = 0.dp
            paddingHorizontal = 12.dp
            elevation = 0.dp
        }
    }
}