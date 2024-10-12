import React, { useState } from "react";
import styles from './style.module.css'; // Обновлено на относительный путь
import FormContainer from './components/FormContainer';
import ToggleContainer from './components/ToggleContainer';

const App: React.FC = () => {
    const [isActive, setIsActive] = useState(false);

    const handleSignUpClick = () => {
        setIsActive(true);
    };

    const handleSignInClick = () => {
        setIsActive(false);
    }

    return (
        <div className={`${styles.container} ${isActive ? styles.active : ''}`} id="container">
            <FormContainer type="sign-up" />
            <FormContainer type="sign-in" />
            <ToggleContainer
                onSignInClick={handleSignInClick}
                onSignUpClick={handleSignUpClick}
            />
        </div>
    );
}

export default App;
