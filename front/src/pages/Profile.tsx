import React from 'react';
import styles from './Profile.module.css';

const Profile: React.FC = () => {
    return (
        <div className={styles.container}>
            <div className={styles.header}>
                <h1 className={styles.name}>John Doe</h1>
                <p className={styles.title}>PC Enthusiast</p>
            </div>
            <div className={styles.content}>
                <div className={styles.section}>
                    <div>
                        <h2 className={styles.sectionTitle}>Personal Information</h2>
                        <div className={styles.infoList}>
                            <InfoItem label="Email" value="john.doe@example.com" />
                            <InfoItem label="Phone" value="+1 (555) 123-4567" />
                            <InfoItem label="Location" value="New York, USA" />
                            <InfoItem label="Member Since" value="January 2020" />
                        </div>
                    </div>
                    <div>
                        <h2 className={styles.sectionTitle}>Account Details</h2>
                        <div className={styles.infoList}>
                            <InfoItem label="Membership" value="Gold" />
                            <InfoItem label="Points" value="1,250" />
                            <InfoItem label="Last Purchase" value="May 15, 2023" />
                            <InfoItem label="Preferred Payment" value="Visa ****1234" />
                        </div>
                    </div>
                </div>
                <div className={styles.activitySection}>
                    <h2 className={styles.sectionTitle}>Recent Activity</h2>
                    <ul className={styles.activityList}>
                        <ActivityItem date="2023-05-15" action="Purchased RTX 3080 Graphics Card" />
                        <ActivityItem date="2023-05-10" action="Viewed 5 products in CPU category" />
                        <ActivityItem date="2023-05-05" action="Wrote a review for 32GB DDR4 RAM Kit" />
                        <ActivityItem date="2023-05-01" action="Added 1TB NVMe SSD to wishlist" />
                    </ul>
                </div>
            </div>
        </div>
    );
};

const InfoItem: React.FC<{ label: string; value: string }> = ({ label, value }) => (
    <div className={styles.infoItem}>
        <span className={styles.infoLabel}>{label}:</span> {value}
    </div>
);

const ActivityItem: React.FC<{ date: string; action: string }> = ({ date, action }) => (
    <li className={styles.activityItem}>
        <span className={styles.activityDate}>{date}</span>
        <span>{action}</span>
    </li>
);

export default Profile;