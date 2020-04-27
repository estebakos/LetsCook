package com.estebakos.letscook.data

import com.estebakos.letscook.base.BaseMapper
import com.estebakos.letscook.data.local.entity.RecipeEntity
import com.estebakos.letscook.data.remote.model.RecipeDetailResponse
import com.estebakos.letscook.data.remote.model.RecipeListResponse
import com.estebakos.letscook.ui.model.RecipeDetailUI
import com.estebakos.letscook.ui.model.RecipeUI

object RecipesDataMapper {

    object RecipeListRemoteToUI :
        BaseMapper<List<RecipeListResponse>, List<RecipeUI>> {
        override fun map(type: List<RecipeListResponse>): List<RecipeUI> {
            return type.map {
                RecipeUI(
                    id = it.id,
                    title = it.title
                )
            }
        }
    }

    object RecipeListUIToCache : BaseMapper<List<RecipeUI>, List<RecipeEntity>> {
        override fun map(type: List<RecipeUI>): List<RecipeEntity> {
            return type.map {
                RecipeEntity(
                    id = it.id,
                    title = it.title
                )
            }
        }
    }

    object RecipeListCacheToUI : BaseMapper<List<RecipeEntity>, List<RecipeUI>> {
        override fun map(type: List<RecipeEntity>): List<RecipeUI> {
            return type.map {
                RecipeUI(
                    id = it.id,
                    title = it.title
                )
            }
        }
    }

    object RecipeDetailRemoteToUI : BaseMapper<RecipeDetailResponse, RecipeDetailUI> {
        override fun map(type: RecipeDetailResponse): RecipeDetailUI {
            return with(type) {
                RecipeDetailUI(
                    id = id,
                    title = title,
                    rating = rating,
                    image = image,
                    instructions = instructions
                )
            }
        }
    }
}