import React from 'react';
import styles from '../style.module.css'; // Обновлено на относительный путь

interface ToggleContainerProps {
    onSignInClick: () => void;
    onSignUpClick: () => void;
}

const ToggleContainer: React.FC<ToggleContainerProps> = ({ onSignInClick, onSignUpClick }) => {
    return (
        <div className={styles['toggle-container']}>
            <div className={styles.toggle}>
                <div className={`${styles['toggle-panel']} ${styles['toggle-left']}`}>
                    <h1>Welcome Back!</h1>
                    <p>Enter your personal details to use all of site features</p>
                    <button className={styles.hidden} onClick={onSignInClick} id="login">
                        Sign In
                    </button>
                </div>
                <div className={`${styles['toggle-panel']} ${styles['toggle-right']}`}>
                    <h1>Hello, Friend</h1>
                    <p>Register with your personal details to use all of site features</p>
                    <button className={styles.hidden} onClick={onSignUpClick} id="register">
                        Sign Up
                    </button>
                </div>
            </div>
        </div>
    );
}

export default ToggleContainer;
