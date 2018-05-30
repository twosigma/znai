import React, {Component} from 'react';
import TocMenu from './TocMenu';
import PanelCollapseButton from './PanelCollapseButton'

class TocPanel extends Component {
    render() {
        const {
            docMeta,
            toc,
            collapsed,
            selected,
            selectedItem,
            onTocItemClick,
            onTocItemPageSectionClick,
            onHeaderClick} = this.props

        const panelClass = "toc-panel" + (collapsed ? " collapsed" : "") + (selected ? " selected" : "")

        return (
            <div className={panelClass}>
                <div className="header">
                    <span>
                        <img width="48" height="48" src="data:image/png;base64,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"/>
                        <span className="toc-panel-header-title"
                              onClick={onHeaderClick}>{docMeta.title + " " + docMeta.type}</span>
                    </span>
                    <PanelCollapseButton isCollapsed={collapsed} onClick={this.toggle}/>
                </div>
                <TocMenu toc={toc}
                         selected={selectedItem}
                         onTocItemPageSectionClick={onTocItemPageSectionClick}
                         onTocItemClick={onTocItemClick}/>
            </div>
        )
    }

    toggle = () => {
        const collapsed = !this.props.collapsed
        this.props.onToggle(collapsed)
    }

    keyDownHandler = (e) => {
        const {selected, collapsed, onNextPage, onPrevPage} = this.props

        if (!selected || collapsed) {
            return
        }

        if (e.key === 'ArrowUp') {
            onPrevPage()
        } else if (e.key === 'ArrowDown') {
            onNextPage()
        }
    }

    componentDidMount() {
        document.addEventListener('keydown', this.keyDownHandler)
    }

    componentWillUnmount() {
        document.removeEventListener('keydown', this.keyDownHandler)
    }
}

export default TocPanel