import React from 'react'
import './BulletList.css'

import DefaultBulletList from './kinds/DefaultBulletList'
import LeftRightTimeLine from './kinds/LeftRightTimeLine'
import Venn from './kinds/Venn'
import RevealBoxes from './kinds/RevealBoxes'
import Steps from './kinds/Steps'
import HorizontalStripes from './kinds/HorizontalStripes'

import {isAllAtOnce} from '../meta/meta'

const types = {LeftRightTimeLine, Venn, Steps}
const presentationTypes = {...types, RevealBoxes, HorizontalStripes}

const BulletList = (props) => {
    const type = listType(props, 'bulletListType')

    if (! type) {
        return <DefaultBulletList {...props}/>
    }

    const Bullets = valueByIdWithWarning(types, type)
    return <Bullets {...props}/>
}

const NoBullets = () => <div className="content-block">No bullets type found</div>

const PresentationBulletList = (props) => {
    const type = presentationListType(props)

    if (type === null) {
        return <DefaultBulletList {...props}/>
    }

    const PresentationBullets = valueByIdWithWarning(presentationTypes, type)
    const isPresentation = typeof props.slideIdx !== 'undefined'

    return <PresentationBullets isPresentation={isPresentation} {...props}/>
}

const presentationNumberOfSlides = (props) => {
    const {content, meta} = props

    const type = presentationListType(props)
    return (type === null || isAllAtOnce(meta)) ? 1 : content.length
}

function valueByIdWithWarning(dict, type) {
    if (! dict.hasOwnProperty(type)) {
        console.warn("can't find bullets list type: " + type)
        return NoBullets
    }

    return dict[type]
}

function presentationListType(props) {
    return listType(props, 'bulletListType') ||
        listType(props, 'presentationBulletListType')
}

function listType(props, key) {
    if (! props.hasOwnProperty('meta')) {
        return null
    }

    return props.meta[key]
}

function slideInfoProvider(props) {
    const type = presentationListType(props)
    if (! type) {
        return {}
    }

    const Bullets = valueByIdWithWarning(presentationTypes, type)
    console.log(props, Bullets)

    return {isFullScreen: Bullets.isPresentationFullScreen}
}

const presentationBulletListHandler = {component: PresentationBulletList,
    numberOfSlides: presentationNumberOfSlides,
    slideInfoProvider: slideInfoProvider}

export {BulletList, presentationBulletListHandler}
