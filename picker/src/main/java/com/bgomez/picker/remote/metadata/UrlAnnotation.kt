package com.bgomez.picker.remote.metadata


/**
 * Annotation used to specify URL on remote services
 *
 * Created by bernatgomez on July,2020
 */
@Target(AnnotationTarget.CLASS)
annotation class UrlAnnotation(val url : String)