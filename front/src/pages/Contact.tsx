import React, { useState } from 'react';
import styles from './Contact.module.css';

const Contact: React.FC = () => {
    const [formData, setFormData] = useState({
        name: '',
        email: '',
        subject: '',
        message: '',
    });

    const [responseMessage, setResponseMessage] = useState('');
    const [errorMessage, setErrorMessage] = useState('');

    const handleChange = (e: React.ChangeEvent<HTMLInputElement | HTMLTextAreaElement | HTMLSelectElement>) => {
        const { name, value } = e.target;
        setFormData((prevData) => ({
            ...prevData,
            [name]: value,
        }));
        setErrorMessage(''); // Сбрасываем сообщение об ошибке при изменении значения
    };

    const handleSubmit = async (e: React.FormEvent) => {
        e.preventDefault();

        // Проверка на заполнение обязательных полей
        if (!formData.name || !formData.email || !formData.subject || !formData.message) {
            setErrorMessage('Please fill in all required fields.'); // Уведомление на английском
            return;
        }

        try {
            const response = await fetch('http://localhost:8080/api/contact', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(formData),
            });

            if (response.ok) {
                setResponseMessage('Your message has been sent successfully!');
                setErrorMessage(''); // Сброс сообщения об ошибке
            } else {
                setResponseMessage('Failed to send your message. Please try again later.');
            }
        } catch (error) {
            console.error('Error:', error);
            setResponseMessage('An error occurred while sending your message.');
        }

        // Сброс формы
        setFormData({ name: '', email: '', subject: '', message: '' });
    };

    return (
        <div className={styles.container}>
            <h1 className={styles.title}>Contact Us</h1>
            <form onSubmit={handleSubmit} className={styles.form}>
                <div className={styles.formGroup}>
                    <label htmlFor="name" className={styles.label}>
                        Name
                    </label>
                    <input
                        type="text"
                        id="name"
                        name="name"
                        value={formData.name}
                        onChange={handleChange}
                        className={styles.input}
                        required
                    />
                </div>
                <div className={styles.formGroup}>
                    <label htmlFor="email" className={styles.label}>
                        Email
                    </label>
                    <input
                        type="email"
                        id="email"
                        name="email"
                        value={formData.email}
                        onChange={handleChange}
                        className={styles.input}
                        required
                    />
                </div>
                <div className={styles.formGroup}>
                    <label htmlFor="subject" className={styles.label}>
                        Subject
                    </label>
                    <select
                        id="subject"
                        name="subject"
                        value={formData.subject}
                        onChange={handleChange}
                        className={styles.select}
                        required
                    >
                        <option value="">Select a subject</option>
                        <option value="general">General Inquiry</option>
                        <option value="support">Technical Support</option>
                        <option value="sales">Sales</option>
                        <option value="other">Other</option>
                    </select>
                </div>
                <div className={styles.formGroup}>
                    <label htmlFor="message" className={styles.label}>
                        Message
                    </label>
                    <textarea
                        id="message"
                        name="message"
                        value={formData.message}
                        onChange={handleChange}
                        rows={4}
                        className={styles.textarea}
                        required
                    ></textarea>
                </div>
                <button type="submit" className={styles.submitButton}>
                    Send Message
                </button>
            </form>
            {errorMessage && <p className={styles.errorMessage}>{errorMessage}</p>}
            {responseMessage && <p className={styles.responseMessage}>{responseMessage}</p>}
        </div>
    );
};

export default Contact;
