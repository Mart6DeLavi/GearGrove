import React from "react";

interface ButtonsPanelProps {
    onContentChange: (newContent: string) => void;
}

const ButtonPanel: React.FC<ButtonsPanelProps> = ({onContentChange}) => {
    return (
        <div className="buttons-panel">
            <button onClick={() => onContentChange('Content for Home')}>Home</button>
            <button onClick={() => onContentChange('Content for About')}>About</button>
            <button onClick={() => onContentChange('Content for Contact')}>Contact</button>
        </div>
    )
}

export default ButtonPanel;