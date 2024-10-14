interface ContentDisplayProps {
    content: string;
}

const ContentDisplay: React.FC<ContentDisplayProps> = ({ content }) => {
    return (
        <div className="content-display">
            <h2>{content}</h2>
        </div>
    )
}

export default ContentDisplay;