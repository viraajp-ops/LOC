import pandas as pd
from sklearn.linear_model import LinearRegression
import numpy as np

file_path = "delhivery.csv"
df = pd.read_csv(file_path)
# Drop rows with missing values in the relevant column
df_clean = df.dropna(subset=['actual_distance_to_destination'])

# Prepare the variables
X = df_clean[['actual_distance_to_destination']]  # Distance (independent variable)
# Let's assume the cost is proportional to distance with some unknown base and rate: cost = base + rate * distance
# We'll simulate cost using a regression model that fits this pattern.
# For training, assume a "cost" using real trip data – we use actual_time as proxy cost for regression training.
# But for now, just fit to find the best linear relationship as if cost = base + rate * distance

# Fit the regression model
model = LinearRegression()
model.fit(X, df_clean['actual_time'])  # Using actual_time as a cost proxy

# Get the slope (rate) and intercept (base cost)
slope = model.coef_[0]
intercept = model.intercept_


